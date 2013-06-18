/**
 * 
 */
package com.opensajux.primefaces.component;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.dnd.Draggable;
import org.primefaces.renderkit.CoreRenderer;

/**
 * @author Sheikh Mohammad Sajid
 *
 */
public class DraggableRenderer extends CoreRenderer {

    @Override
    public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        Draggable draggable = (Draggable) component;
        String clientId = draggable.getClientId(facesContext);
        String target = findTarget(facesContext, draggable);
        String dashboard = draggable.getDashboard();

        startScript(writer, clientId);

        writer.write("$(function() {");
        
        writer.write("PrimeFaces.cw('Draggable','" + draggable.resolveWidgetVar() + "',{");
        writer.write("id:'" + clientId + "'");
        writer.write(",target:'" + target + "'");
        writer.write(",cursor:'" + draggable.getCursor() + "'");

        //Configuration
        if(draggable.isDisabled())
            writer.write(",disabled:true");
        if(draggable.getAxis() != null)
            writer.write(",axis:'" + draggable.getAxis() + "'");
        if(draggable.getContainment() != null)
            writer.write(",containment:'" + draggable.getContainment() + "'");
        if(draggable.getHelper() != null)
            writer.write(",helper:'" + draggable.getHelper() + "'");
        if(draggable.isRevert())
            writer.write(",revert:'invalid'");
        if(draggable.getZindex() != -1)
            writer.write(",zIndex:" + draggable.getZindex());
        if(draggable.getHandle() != null)
            writer.write(",handle:'" + draggable.getHandle() + "'");
        if(draggable.getOpacity() != 1.0)
            writer.write(",opacity:" + draggable.getOpacity());
        if(draggable.getStack() != null)
            writer.write(",stack:'" + draggable.getStack() + "'");
        if(draggable.getGrid() != null)
            writer.write(",grid:[" + draggable.getGrid() + "]");
        if(draggable.getScope() != null)
            writer.write(",scope:'" + draggable.getScope() + "'");

        if(draggable.isSnap()) {
            writer.write(",snap:true");
            writer.write(",snapTolerance:" + draggable.getSnapTolerance());
            if(draggable.getSnapMode() != null)
                writer.write(",snapMode:'" + draggable.getSnapMode() + "'");
        }

        //Dashboard support
        if(dashboard != null) {
            Dashboard db = (Dashboard) draggable.findComponent(dashboard);
            if(db == null) {
                throw new FacesException("Cannot find dashboard \"" + dashboard + "\" in view");
            }

            writer.write(",connectToSortable:'.ui-dashboard-column'");
        }

        writer.write("});");

        writer.write("});");

        endScript(writer);
    }

    protected String findTarget(FacesContext facesContext, Draggable draggable) {
        String _for = draggable.getFor();

        if(_for != null) {
            UIComponent component = draggable.findComponent(_for);
            if(component == null)
                throw new FacesException("Cannot find component \"" + _for + "\" in view.");
            else
                return component.getClientId(facesContext);
        } else {
            return draggable.getParent().getClientId(facesContext);
        }
    }
}

