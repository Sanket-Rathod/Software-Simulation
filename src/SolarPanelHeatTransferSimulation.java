

/*
 * Heat is transferred from solar panel -> pump -> storage tank. 
 * 
 * Heat is transferred via conduction, convection and radiation. For simplicity, heat transfer at panel in the form of conduction is considered. Then heat is transferred through pump to storage tank. 
 * Equations used are - 
 * Heat Transfer rate(Joule/Second) = K * A * (T2 - T1)/thickness
 * K is Thermal coefficient of the material
 * A is area of surface
 * T2-T1 is the temperature difference 
 * thickness is the change in temperature separated by the distance 
 * 
 * Heat transfer(Joules) = Cs * M * (T2 -T1)
 * Cs is specific heat of the material 
 * M is mass
 * T2-T1 is the temperature difference 
 * 
 * 
 * The simulator is configurable based on the specifications of the system. For example, storage tank can be liquid water or any other liquid/gas. 
 * Simulator will ask users to submit the specifications of the system from the console. 
 *  
 * Sample System details are as follows - 
 		double initialTemperaturePanel = 70.0; // in degrees Celsius
        double initialTemperatureTank = 20.0;  // in degrees Celsius
        double panelArea = 2.0;                 // in square meters
        double tankVolume = 100000.0;            // 100 Litres, 
        double panelThermalCoefficient = 1.1; //  thermal coefficient for glass is 1.1 W/mK
        double specificHeatTank = 4.18; //Assumed water in storage tank; specific heat for water is 4.18J/gK
        double panelThickness = 0.03; //in meters
        int time = 3600; // in seconds
 * 
 * Heat transfer will be calculated and the output will be displayed for each minute in the console. 
 * The simulator is calculated based on assumption that there in no heat loss at panel, pump or tank. 
 * Another assumption is made that outside and panel temperature is constant. In real world scenario, outside temperature would change. For real world scenarios, reactive programming or observer pattern can be used where panelTemperature variable can be subscribed to the sensor or outside temperature of the system.
 * 
 * To run the code in terminal use below commands - 
 * Step 1: Navigate to src of this project in terminal
 * Step 2: To compile, execute javac SolarPanelHeatTransferSimulation.java command
 * Step 3: To run, execute java SolarPanelHeatTransferSimulation command
 * Step 4: Provide the specifications of the system
 */

import java.util.*;

public class SolarPanelHeatTransferSimulation {

	public static void main(String[] args) {
		runSimulator();
    }

	private static void runSimulator() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Simulator started.");
			System.out.println("Please provide specifications of the system.");
			System.out.println("Please provide intial temperature of storage tank : ");
			double initialTemperatureTank = scanner.nextDouble();
			System.out.println("Please provide intial temperature of solar panel : ");
			double initialTemperaturePanel = scanner.nextDouble();
			System.out.println("Please provide area of solar panel in square meters : ");
	        double panelArea = scanner.nextDouble();
	        System.out.println("Please provide volume of tank in grams or milliliters : ");
	        double tankVolume = scanner.nextDouble();
	        System.out.println("Please provide thermal conductivity of the material of panel(common values are between 0.9 to 1.2 W/mK) : ");
	        double panelThermalCoefficient = scanner.nextDouble();
	        System.out.println("Please provide specific heat of the storage tank(specific heat for water is 4.18J/gK) : ");
	        double specificHeatTank = scanner.nextDouble();
	        System.out.println("Please provide panel thickness in meters : ");
	        double panelThickness = scanner.nextDouble();
	        System.out.println("Please provide time simulation time in seconds	 : ");
	        int time = scanner.nextInt();
	        
	        double panelTemperature = initialTemperaturePanel; 
	        double tankTemperature = initialTemperatureTank;
	        
	        double tempDifference = panelTemperature - tankTemperature; 
	        double heatTransferRateInPanel = (panelThermalCoefficient * panelArea * tempDifference)/(panelThickness); // in J/s
	        double changeInTemperature = heatTransferRateInPanel /(specificHeatTank * tankVolume);//change inn temperature at tank
	        double totalHeatTransferred = 0.0;//in Joules
	        System.out.println("TIME(Min) \t TANK TEMPERATURE(K/C) \t TOTAL HEAT TRANSFERRED(Joules)");
	        for(int t=1;t<=time;t++) {
	        	tankTemperature += changeInTemperature;//updated tank temperature is the input for the solar panel for next iterations. 
	        	tempDifference = panelTemperature - tankTemperature;
	        	if(tempDifference<=0) {// Thermal equilibrium is reached before end of simulation time
	        		
	        		System.out.println("Thermal equilibrium reached at " + t/60 + " minutes and " + t%60 +" seconds.");//try catch block
	        		break;
	        	}
	        	heatTransferRateInPanel = (panelThermalCoefficient * panelArea * tempDifference)/(panelThickness);
	        	totalHeatTransferred += heatTransferRateInPanel;
	        	changeInTemperature = heatTransferRateInPanel /(specificHeatTank * tankVolume);
	        	if(t%60==0) {//display per minute
	        		//We can also edit solar panel temperature for every minute here if the outside temperature changes in real time.
	        		System.out.printf("" +t/60 + " \t \t %.2f \t \t \t %.2f \n",tankTemperature, totalHeatTransferred );
	        	}
	        }
		} catch (NumberFormatException | InputMismatchException e) {
			System.err.println("Error reading input: " + e.getMessage());
		} catch (Exception e) {// Handle other exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }finally {
			scanner.close();
			System.out.println("Simulator closed.");
		}
	}
}
