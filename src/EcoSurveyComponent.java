import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class EcoSurveyComponent {
    private final class Species {

        //Should these fields be public?
        /**
         * colloquial name
         */
        private String name;
        /**
         * scientific name
         */
        private String trueName;
        /**
         * ideal population density
         */
        private double density;

    }

    //is this redundant. Should I just create another private field for count?
    private Map<Species, Integer> speciesCount;

    private double area;

    private int totalSpecies;

    private void createNewRep() {
        this.totalSpecies = 0;
        this.area = 0;
        this.speciesCount = new Map1L<>();
    }

    public EcoSurveyComponent() {
        this.createNewRep();
    }

    //private method
    private boolean hasSpecies(String name) {
        //this function seems all wrong
        boolean speciesFound = false;
        for (Map.Pair<Species, Integer> s : this.speciesCount) {
            if (s.key().name == name) {
                speciesFound = true;
            }
        }
        return speciesFound;
    }

    //I think this is a kernel function. I do want client to use it.
    //Is it ok if it accesses fields/representation.
    private void addSpecies(String name, String trueName, double density) {
        assert !this.hasSpecies(name) : "Violation of species does not exist";

        Species s1 = new Species();
        s1.name = name;
        s1.trueName = trueName;
        s1.density = density;
        this.speciesCount.add(s1, 0);
        this.totalSpecies++;
    }

    //kernel. Should these methods be public?
    private void setCount(String name, int count) {
        assert this.hasSpecies(name) : "Violation of species exists";

        Species s1 = this.getSpecies(name);
        this.speciesCount.replaceValue(s1, count);

    }

    private Species getSpecies(String name) {
        assert this.hasSpecies(name) : "Violation of species exists";

        Species s1 = new Species();
        for (Map.Pair<Species, Integer> s : this.speciesCount) {
            if (s.key().name == name) {
                //aliasing? May need to make a instance methods
                s1 = s.key();
            }
        }
        return s1;
    }

    private void setCoordinates(double a, double b, double c, double d) {
        //double four = 4d;
        this.area = (a + b + c + d) / 4;
        //
        /**
         * obviously this formula is not good enough. I want to calculate the
         * area of the survey plot in this function. Not sure if I need to track
         * the coordinates afterwards
         *
         */
    }

    public String getStatus(String name) {
        Species s1 = this.getSpecies(name);
        int count = this.speciesCount.value(s1);
        double density = s1.density;
        String status = "";
        if (count / this.area < density) {
            status = "Critical";
        } else if (count / this.area > 2 * density) {
            status = "Thriving";
        } else {
            status = "Normal";
        }
        return status;
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //This made me realize I need a better way to iterate through
        //the existing species in the object
        String[] animalList = { "Giant Panda", "Bengal Tiger",
                "Reticulated Python", "Malayopython reticulatus" };

        EcoSurveyComponent metroNationalPark = new EcoSurveyComponent();
        metroNationalPark.setCoordinates(1, 2, 3, 4);
        metroNationalPark.addSpecies(animalList[0], "Ailuropoda melanoleuca",
                0.01);
        metroNationalPark.addSpecies(animalList[1], "Panthera tigris", 0.001);
        metroNationalPark.addSpecies(animalList[2], "Malayopython reticulatus",
                0.001);
        metroNationalPark.addSpecies(animalList[3], "Bubalus arnee", 0.001);

        for (int i = 0; i < animalList.length; i++) {
            out.println(metroNationalPark.getSpecies(animalList[i]).name
                    + " Scientific Name: "
                    + metroNationalPark.getSpecies(animalList[i]).trueName);
            out.println("Its current status is: "
                    + metroNationalPark.getStatus(animalList[i]));
        }
        in.close();
        out.close();
    }

}
