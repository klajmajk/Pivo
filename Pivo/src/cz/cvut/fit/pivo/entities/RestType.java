/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

/**
 *
 * @author Adam
 */
public enum RestType {

    VYSTIRKA,
    PEPTONIZACE,
    NIZSI_CUKROTVORNA,
    VYSSI_CUKROTVORNA,
    ODRMUTOVACI,
    VAR_RMUT;

    public RestType getNext() {
        return this.ordinal() < RestType.values().length - 1
                ? RestType.values()[this.ordinal() + 1]
                : null;
    }

    @Override
    public String toString() {
        switch (this) {
            case VYSTIRKA:
                return "Vystírka";
            case PEPTONIZACE:
                return "Peptonizace";
            case NIZSI_CUKROTVORNA:
                return "Nižší cukrotvorná prodleva";
            case VYSSI_CUKROTVORNA:
                return "Vyšší cukrotvorná prodleva";
            case ODRMUTOVACI:
                return "Odrmutování";
            case VAR_RMUT:
                return "Var";
        }
        return "";
    }

}
