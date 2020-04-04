// utility functions for customized try catch
function _isError(arg) {
    return typeof arg === "object" && arg !== null && Object.getPrototypeOf(arg) === _Error.prototype
}

function _Error(which, what) {
    this.which = which
    this.what = what
    this.where = []
}

_Error.prototype._catch = function(which) {
    const self = this
    return function(f) {
        if (self.which === which) {
            const wrapper = {_catch: () => () => wrapper, _endCatch: () => f()}
            return wrapper
        }
        return self
    }
}

_Error.prototype._printStackTrace = function() {
    console.error("Uncaught error: " + this.which)
    console.error(this.what)
    console.error("at:")
    for (const f of this.where)
        console.error(f)
}

_Error.prototype._endCatch = function() {
    return this
}

const _throw = (which, what) => new _Error(which, what)

const _try = (f, ...args) => {
    const res = f.apply(null, args)
    if (_isError(res)) {
        res.where.unshift(f)
        return res
    }
    const wrapper = {_catch: () => () => wrapper, _endCatch: () => res}
    return wrapper
}


// use case
function b() {
    return  _try(function a(x, y) {
                if (y == 0)
                    return _throw("ArithmeticException", "/ by zero")
                return x / y
            }, 1, 0)
}

function c() {
    return  _try(b)
}

function d() {
    return _try(c)
}

const answer = _try(d)
                ._catch("xException") (
                    () => "xException occurred"
                )
                ._catch("yException") (
                    () => "yException occurred"
                )
                // uncomment the following clause to catch the exception
                // ._catch("ArithmeticException") (
                //     () => "Arithmetic exception occurred"
                // )
                ._catch("zException") (
                    () => "zException occurred"
                )
                ._endCatch()

if (!_isError(answer))
    console.log(answer)
else
    answer._printStackTrace()
