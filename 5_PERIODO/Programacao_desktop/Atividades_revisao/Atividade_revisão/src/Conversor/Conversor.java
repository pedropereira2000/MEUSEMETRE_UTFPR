package Conversor;

class Conversor
{

	public static void main(String args[])
	{
		Conversor c1 = new Conversor();
		System.out.println(c1.celsiusFromFehrenheit(37.8));
		System.out.println(c1.fehrenheitFromCelsius(-20.3));
		System.out.println(c1.celsiusFromKelvin(36));
		System.out.println(c1.fehrenheitFromKelvin(-21));
		System.out.println(c1.KelvinFromFehrenheit(282));
		System.out.println(c1.KelvinFromCelsius(278));
	}


	double celsiusFromFehrenheit(double c)
	{
		return 9.0 * c / 5.0 + 32.0;
	}
	double fehrenheitFromCelsius(double f)
	{
		return 5.0 * (f - 32.0) / 9.0;
	}
	double celsiusFromKelvin(double c)
	{
		return c + 273.15;
	}
	double fehrenheitFromKelvin(double f)
	{
		return celsiusFromKelvin(fehrenheitFromCelsius(f));
	}
	double KelvinFromFehrenheit(double k)
	{
		return celsiusFromFehrenheit(KelvinFromCelsius(k));
	}
	double KelvinFromCelsius(double k)
	{
		return k - 273.15;
	}

	
}