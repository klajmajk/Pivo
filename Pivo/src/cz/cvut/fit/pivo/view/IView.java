/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view;

/**
 *
 * @author Adam
 */

public interface IView {
	public void show();
        public void notifyView();
        public void reset();
        public void start(); 
}
