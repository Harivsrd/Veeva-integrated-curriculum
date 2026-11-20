import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PopulationSystem {
    private HashMap<String, HashMap<String, Long>> states = new HashMap<>();

    public boolean isStateExists(String stateName) {
        return states.containsKey(stateName);
    }

    public boolean isCityExists(String stateName, String cityName) {
        return states.containsKey(stateName) && states.get(stateName).containsKey(cityName);
    }

    public void addNewState(String stateName) {
        if (isStateExists(stateName)) {
            System.out.println("Error: State '" + stateName + "' already exists in the directory!");
        } else {
            states.put(stateName, new HashMap<>());
            System.out.println("Success: State '" + stateName + "' added successfully.");
        }
    }

    public void addNewCity(String stateName, String cityName, long population) {
        if (!isStateExists(stateName)) {
            System.out.println("Error: State '" + stateName + "' does not exist. Please add the state first.");
            return;
        }
        HashMap<String, Long> cities = states.get(stateName);
        if (cities.containsKey(cityName)) {
            System.out.println("Error: City '" + cityName + "' already exists in '" + stateName + "'. Use update option instead.");
        } else {
            cities.put(cityName, population);
            System.out.println("Success: City '" + cityName + "' added to '" + stateName + "' with population " + population + ".");
        }
    }

    public void updateCityPopulation(String stateName, String cityName, long population) {
        if (!isStateExists(stateName)) {
            System.out.println("Error: State '" + stateName + "' does not exist.");
            return;
        }
        if (!isCityExists(stateName, cityName)) {
            System.out.println("Error: City '" + cityName + "' does not exist in '" + stateName + "'.");
            return;
        }
        states.get(stateName).put(cityName, population);
        System.out.println("Success: Population for '" + cityName + "' in '" + stateName + "' updated to " + population + ".");
    }

    public Long getPopulation(String stateName, String cityName) {
        if (!isStateExists(stateName)) {
            System.out.println("Error: State '" + stateName + "' does not exist.");
            return null;
        }
        if (!isCityExists(stateName, cityName)) {
            System.out.println("Error: City '" + cityName + "' does not exist in '" + stateName + "'.");
            return null;
        }
        return states.get(stateName).get(cityName);
    }

    public void displayStateAggregation(String stateName) {
        if (!isStateExists(stateName)) {
            System.out.println("Error: State '" + stateName + "' does not exist.");
            return;
        }
        HashMap<String, Long> cities = states.get(stateName);
        if (cities.isEmpty()) {
            System.out.println("State '" + stateName + "' has no cities recorded yet.");
            return;
        }
        System.out.println("\n=== Aggregation for State: " + stateName + " ===");
        long totalPopulation = 0;
        for (Map.Entry<String, Long> entry : cities.entrySet()) {
            System.out.println("  * " + entry.getKey() + " : " + entry.getValue());
            totalPopulation += entry.getValue();
        }
        System.out.println("----------------------------------------");
        System.out.println("Combined Total Population: " + totalPopulation);
    }

    public void filterCitiesByThreshold(long threshold) {
        boolean found = false;
        System.out.println("\n=== Cities Exceeding Population Threshold: " + threshold + " ===");
        for (Map.Entry<String, HashMap<String, Long>> stateEntry : states.entrySet()) {
            String stateName = stateEntry.getKey();
            for (Map.Entry<String, Long> cityEntry : stateEntry.getValue().entrySet()) {
                if (cityEntry.getValue() > threshold) {
                    System.out.println("  * " + cityEntry.getKey() + " (" + stateName + ") : " + cityEntry.getValue());
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No cities found exceeding the threshold of " + threshold + ".");
        }
    }

    public void findCityGlobal(String cityName) {
        boolean found = false;
        for (Map.Entry<String, HashMap<String, Long>> stateEntry : states.entrySet()) {
            String stateName = stateEntry.getKey();
            HashMap<String, Long> cities = stateEntry.getValue();
            if (cities.containsKey(cityName)) {
                System.out.println("Result: City '" + cityName + "' is located in State: '" + stateName + "' with population " + cities.get(cityName) + ".");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Error: City '" + cityName + "' does not exist in any recorded state.");
        }
    }

    public void displayAll() {
        if (states.isEmpty()) {
            System.out.println("Directory is empty.");
            return;
        }
        System.out.println("\n=== Complete Population Directory ===");
        for (Map.Entry<String, HashMap<String, Long>> stateEntry : states.entrySet()) {
            System.out.print(stateEntry.getKey() + " : ");
            HashMap<String, Long> cities = stateEntry.getValue();
            if (cities.isEmpty()) {
                System.out.print("[No cities recorded]");
            } else {
                int count = 0;
                for (Map.Entry<String, Long> cityEntry : cities.entrySet()) {
                    if (count > 0) System.out.print(", ");
                    System.out.print(cityEntry.getKey() + " --> " + cityEntry.getValue());
                    count++;
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PopulationSystem system = new PopulationSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- India Population Directory System ---");
            System.out.println("1. Add State");
            System.out.println("2. Add City to State");
            System.out.println("3. Update Population of City");
            System.out.println("4. Display All Data");
            System.out.println("5. Get Population of City (Query 1)");
            System.out.println("6. State-Level Aggregation (Query 2)");
            System.out.println("7. Global Search by City Name (Query 4)");
            System.out.println("8. Retrieve Cities Exceeding Threshold (Query 3)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice (1-9): ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter state name: ");
                    system.addNewState(sc.next());
                    break;
                case 2:
                    System.out.print("Enter state name: ");
                    String stateAdd = sc.next();
                    System.out.print("Enter city name: ");
                    String cityAdd = sc.next();
                    System.out.print("Enter population: ");
                    long popAdd = sc.nextLong();
                    system.addNewCity(stateAdd, cityAdd, popAdd);
                    break;
                case 3:
                    System.out.print("Enter state name: ");
                    String stateUpd = sc.next();
                    System.out.print("Enter city name: ");
                    String cityUpd = sc.next();
                    System.out.print("Enter new population: ");
                    long popUpd = sc.nextLong();
                    system.updateCityPopulation(stateUpd, cityUpd, popUpd);
                    break;
                case 4:
                    system.displayAll();
                    break;
                case 5:
                    System.out.print("Enter state name: ");
                    String stateQ1 = sc.next();
                    System.out.print("Enter city name: ");
                    String cityQ1 = sc.next();
                    Long popQ1 = system.getPopulation(stateQ1, cityQ1);
                    if (popQ1 != null) {
                        System.out.println("Population of " + cityQ1 + ", " + stateQ1 + " is: " + popQ1);
                    }
                    break;
                case 6:
                    System.out.print("Enter state name: ");
                    system.displayStateAggregation(sc.next());
                    break;
                case 7:
                    System.out.print("Enter city name for global search: ");
                    system.findCityGlobal(sc.next());
                    break;
                case 8:
                    System.out.print("Enter population threshold limit: ");
                    system.filterCitiesByThreshold(sc.nextLong());
                    break;
                case 9:
                    System.out.println("Exiting application. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 9.");
            }
        }
    }
}