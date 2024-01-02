

 Heat is transferred from solar panel -> pump -> storage tank. 
  
 Heat is transferred via conduction, convection and radiation. For simplicity, heat transfer at panel in the form of conduction is considered. Then heat is transferred through pump to storage tank. 
 Equations used are - 
 Heat Transfer rate(Joule/Second) = K * A * (T2 - T1)/thickness
 K is Thermal coefficient of the material
 A is area of surface
 T2-T1 is the temperature difference 
 thickness is the change in temperature separated by the distance 
  
 Heat transfer(Joules) = Cs * M * (T2 -T1)
 Cs is specific heat of the material 
 M is mass
 T2-T1 is the temperature difference
  
 The simulator is configurable based on the specifications of the system. For example, storage tank can be liquid water or any other liquid/gas. 
 Simulator will ask users to submit the specifications of the system from the console. 

 Sample System details are as follows - 
        double initialTemperatureTank = 20.0;  // in degrees Celsius
 		double initialTemperaturePanel = 70.0; // in degrees Celsius
        double panelArea = 2.0;                 // in square meters
        double tankVolume = 100000.0;            // 100 Litres, 
        double panelThermalCoefficient = 1.1; //  thermal coefficient for glass is 1.1 W/mK
        double specificHeatTank = 4.18; //Assumed water in storage tank; specific heat for water is 4.18J/gK
        double panelThickness = 0.03; //in meters
        int time = 3600; // in seconds
        
        
 Heat transfer will be calculated and the output will be displayed for each minute in the console. 
 The simulator is calculated based on assumption that there in no heat loss at panel, pump or tank. 
 Another assumption is made that outside and panel temperature is constant. In real world scenario, outside temperature would change. For real world scenarios, reactive programming or observer pattern can be used where panelTemperature variable can be subscribed to the sensor or outside temperature of the system.
 
 To run the code in terminal use below commands - 
 Step 1: Navigate to src of this project in terminal
 Step 2: To compile, execute javac SolarPanelHeatTransferSimulation.java command
 Step 3: To run, execute java SolarPanelHeatTransferSimulation command
 Step 4: Provide the specifications of the system
