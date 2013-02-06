package cz.fit.cvut.dpo.mvc.swing;

import cz.fit.cvut.dpo.mvc.command.EnumShape;
import cz.fit.cvut.dpo.mvc.controller.FacadeController;
import cz.fit.cvut.dpo.mvc.model.IModel;
import cz.fit.cvut.dpo.mvc.objects.AbstractShape;
import cz.fit.cvut.dpo.mvc.objects.Circle;
import cz.fit.cvut.dpo.mvc.objects.Position;
import cz.fit.cvut.dpo.mvc.objects.Square;
import cz.fit.cvut.dpo.mvc.view.AbstractView;
import cz.fit.cvut.dpo.mvc.view.IView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author honza
 */
public class CanvasView extends AbstractView {

    public CanvasView(FacadeController controller, IModel model) {
        super(controller, model);
        initComponents();
    }

    protected void initComponents() {
        addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 388, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 427, Short.MAX_VALUE));
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        Position pos;
        switch (evt.getButton()) {
            case MouseEvent.BUTTON1:
                pos = new Position(evt.getPoint().x, evt.getPoint().y);
                controller.createShape(EnumShape.CIRCLE, pos);
                break;
            case MouseEvent.BUTTON3:
                pos = new Position(evt.getPoint().x, evt.getPoint().y);
                controller.createShape(EnumShape.SQUARE, pos);
                break;
            default://do nothing
                break;
        }
    }

    @Override
    public void notifyView() {
        //ziskame z modelu data a ta data vykreslime
        allShapes = model.getAllShapes();
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (AbstractShape abstractShape : allShapes) {
            switch (abstractShape.getType()) {
                case CIRCLE:
                    Random r=new Random();                    
                    Circle circle = (Circle) abstractShape;
                    g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
                    g.drawOval(circle.getPosition().x, circle.getPosition().y, circle.getRadius(), circle.getRadius());
                    break;

                case SQUARE:
                    Square square = (Square) abstractShape;
                    g.setColor(Color.red);
                    g.drawRect(square.getPosition().x, square.getPosition().y, square.getSide(), square.getSide());                    
                    break;
            }
        }

    }
}
