/**
 *
 * EcoSurvey interface with secondary methods.
 *
 * Secondary functions for the Eco Survey Component
 *
 * Component allows for extraction and insertion of new species as well as
 * direct maniuplation of species objects.
 *
 * Date field allows for potential tracking of a subSet of ecoSurvey objects
 * that all act to track a single location.
 *
 * Secondary functions built for future potential analysis and examination.
 */

public interface EcoSurvey extends EcoSurveyKernel {

    /**
     * Calculates the status of a particular species based on desired density
     * and current count.
     *
     * @param name
     *            name of species whose status is being checked.
     * @return (Critical, Thriving, or Normal)
     */
    String status(String name);

    /**
     * determine the overall status of the ecoystem based on all species.
     *
     * @return (Critical, Thriving, or Normal)
     */
    String ecoStatus();

    /**
     * Return the species whose total count is the i-th greatest count
     *
     * @param i
     * @return Species object
     */
    Species topSpecies(int i);

    /**
     * Return the species whose total count is the j-th lowest count
     *
     * @param i
     * @return Species object
     */
    Species lowSpecies(int i);

}
