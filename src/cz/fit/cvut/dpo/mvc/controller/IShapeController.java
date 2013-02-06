/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.dpo.mvc.controller;

import cz.fit.cvut.dpo.mvc.command.EnumShape;
import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import cz.fit.cvut.dpo.mvc.objects.Position;

/**
 *
 * @author honza
 */
public interface IShapeController {
    public void changePosition(AbstractShape shape, Position position);
    public void changeSize(AbstractShape shape, int size) throws ClassCastException;
}
