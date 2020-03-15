package ictgradschool.industry.introtojava.simplemaths;

/**
 * Programming for Industry - Introduction to Java
 * Programming practice
 *
 * Note: Put your answers in between the "// Answer here //" comments. Do not modify other parts of the class.
 */
public class SimpleMaths {

	/**
	 * 1. Convert kilograms to pounds.
	 */
	public double kilogramsToPounds(int kilograms) {
		double pounds = 0.0;
		pounds = kilograms * 2.20462;
		System.out.println(kilograms + " kgs is " + pounds + " pounds");
		return pounds;
	}

	/**
	 * 2. Convert Celsius to Fahrenheit.
	 */
	public double convertCelsiusToFahrenheit(double celsius) {
		double farenheit = 0.0;
		// Answer here
		farenheit = 32 + 9.0 / 5 * celsius;
		//
		System.out.println(celsius + " degrees Celsius is " + farenheit + " degrees Fahrenheit");
		return farenheit;
	}

	/**
	 * 3. Calculate compound interest.
	 */
	public double getCompoundInterestValue(double principal, double rate, int years) {
		double value = 0;
		// Answer here
		value = principal * Math.pow(1 + rate / 100.0, years);
		//
		System.out.println("Amount at the end of " + years + " years: $" + value);
		return value;
	}

	/**
	 * 4. Calculate my BMI.
	 */
	public double getMyBMI(double weight, double height) {
		double myBMI = 0;
		// Answer here
		// avoiding calling Math.pow may make it faster
		myBMI = weight / height / height;
		//
		System.out.println("Your BMI is " + myBMI);
		return myBMI;
	}

	/**
	 * 5. Calculate the volume of a sphere.
	 */
	public double getSphereVolume(double radius) {
		double volume = 0;
		// Answer here
		volume = Math.round(4.0 / 3 * Math.PI * Math.pow(radius, 3));
		//
		System.out.println("Volume is " + volume + " cubic cms.");
		return volume;
	}

	/**
	 * 6. Convert total days into weeks and days.
	 */
	public void convertTotalDaysIntoWeeksAndDays(int days) {
		int weeks = 0;
		int remainingDays = 0;
		// Answer here
		int daysInWeek = 7;
		weeks = days / daysInWeek;
		remainingDays = days % daysInWeek;
		//
		System.out.println("This is " + weeks + " weeks and " + remainingDays + " days.");
	}

	/**
	 * 7. Determine smaller integer.
	 */
	public void findSmallerInteger(int a, int b) {
		int smallerInt = 0;
		// Answer here
		smallerInt = Math.min(a, b);
		//
		System.out.println(smallerInt);
	}


	/**
	 * Don't edit this - but read/use this for testing if you like.
	 */
	public static void main(String[] args) {
		SimpleMaths cr = new SimpleMaths();

		// 1. Convert kilograms to pounds.
		cr.kilogramsToPounds(55); // 55 kgs is 121.2541 pounds
		cr.kilogramsToPounds(100); // 100 kgs is 220.462 pounds
		cr.kilogramsToPounds(0); // 0 kgs is 0.0 pounds

		// 2. Convert Celsius to Fahrenheit.
		cr.convertCelsiusToFahrenheit(19.5); // 19.5 degrees Celsius is 67.1 degrees Fahrenheit
		cr.convertCelsiusToFahrenheit(-12.8); // -12.8 degrees Celsius is 8.96 degrees Fahrenheit
		cr.convertCelsiusToFahrenheit(0); // 0.0 degrees Celsius is 32.0 degrees Fahrenheit

		// 3. Calculate compound interest.
		cr.getCompoundInterestValue(2500.00, 5.5, 20); // Amount at the end of 20 years: $7294.3937265102195
		cr.getCompoundInterestValue(100.50, 3.8, 1); // Amount at the end of 1 years: $104.319
		cr.getCompoundInterestValue(100000, 18.0, 5); // Amount at the end of 5 years: $228775.77567999996

		// 4. Calculate my BMI.
		cr.getMyBMI(65.00, 1.91); // Your BMI is 17.81749403799238
		cr.getMyBMI(61.54, 1.64); // Your BMI is 22.8807257584771
		cr.getMyBMI(78.61, 1.42); // Your BMI is 38.98532037294188

		// 5. Calculate the volume of a sphere.
		cr.getSphereVolume(0); // Volume is 0.0 cubic cms.
		cr.getSphereVolume(2.8); // Volume is 91.95232257547082 cubic cms.
		cr.getSphereVolume(56); // Volume is 735618.5806037667 cubic cms.

		// 6. Convert total days into weeks and days.
		cr.convertTotalDaysIntoWeeksAndDays(0); // This is 0 weeks and 0 days.
		cr.convertTotalDaysIntoWeeksAndDays(25); // This is 3 weeks and 4 days.
		cr.convertTotalDaysIntoWeeksAndDays(14); // This is 2 weeks and 0 days.
		cr.convertTotalDaysIntoWeeksAndDays(3); // This is 0 weeks and 3 days.

		// 7. Determine smaller integer.
		cr.findSmallerInteger(1, 2); // 1
		cr.findSmallerInteger(-10, -1000); // -1000
		cr.findSmallerInteger(1, 1); // 1
	}

}

