/**
 * EcoSurvey Kernel with kenrel methods.
 *
 * Component is meant to model the eco-diversity of an ecologically significant
 * area.
 *
 * Species object is housed within the class. Used to model specific species
 * that inhabit the ecosystem Species attributes include: Name, trueName (latin
 * name), and (desired) density
 *
 * Component must house date and every species object assigned to it.
 *
 * Most methods are just getters and setters and gives clients full access to
 * species object to manipulate values as desired.
 *
 */

public interface EcoSurveyKernel extends Standard<EcoSurvey> {
    /*
     * Species record object Made up of 3 fields: Name, trueName, density.
     */
    public final record Species(String name, String trueName, double density) {
    }

    /**
     * Creates a species object with the appropriate values from parameters.
     *
     * @param name:
     *            colouqial name of the species
     *
     * @param trueName:
     *            latin name of the species
     *
     * @param density:
     *            ideal/desired population density of this species
     *
     * @return species with the appropriate values
     */

    Species createSpecies(String name, String trueName, double density);

    /**
     * Adds {@code spec} to the Ecosurvey component
     *
     * @param spec:
     *
     * @aliases reference {@code spec}
     * @updates this
     * @requires {@code spec does not exist within this}
     *           {@code spec.name does not equal any spec.name within this}
     * @ensures {@code spec exists within #this}
     *
     */
    void addSpecies(Species spec);

    /**
     * Retreive a species from this with name {@code name}
     *
     * @param name:
     *            name of the species
     * @return the species with @code name = species.name
     */

    Species getSpecies(String name);

    /**
     * Remove {@code species} from the component
     *
     * @param spec
     *            species object to be removed
     * @updates this |species| = #|species| - 1
     */

    void removeSpecies(Species spec);

    /**
     * Checks if a species object with species.name = {@code name}
     *
     * @param name
     *            name of the speices
     * @return boolean
     */

    boolean hasSpecies(String name);

    /**
     * Returns the number of species present within the object
     *
     * @return total number of species objects housed within this.
     */
    int speciesCount();

    /**
     * Remove an arbitrary species from this
     *
     * @return an aribtrary species that was in #this
     *
     * @updates this |species| = #|species| - 1
     *
     */
    Species removeAny();

    /**
     * Set the date for when this data was relevant
     *
     * @param date:
     *            the date to be assigned
     * @updates this
     */
    void setDate(Date date);

    /**
     * Get the date for when this data was relevant
     *
     * @return the relevant date
     */
    Date getDate();

    /**
     * Sets the area of the ecoSurvey component representation. Calculates based
     * on length of a side of a square. Potential for future improvements.
     *
     * @param length:
     *            Length of a side of a square in meters
     */
    void setCoordinates(int length);

    /**
     * Return the area of this
     *
     * @return int representing the area of this in meters
     */
    int getArea();

}
