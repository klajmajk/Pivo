/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.dpo.mvc.command;

import cz.fit.cvut.dpo.mvc.model.IModel;

/**
 *
 * @author honza
 */
public abstract class ICommand {
    
    protected IModel model;

    public ICommand(IModel model) {
        this.model = model;
    }
    
    public abstract void execute();
}
