package com.opensajux.view;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.separator.Separator;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.opensajux.entity.LayoutColumn;
import com.opensajux.entity.LayoutRow;
import com.opensajux.entity.Page;
import com.opensajux.entity.WebletPreferences;
import com.opensajux.service.PageService;

@RequestScoped
@Named
public class LayoutBean implements Serializable {
	private static final long serialVersionUID = -6738534794733287767L;

	private Panel layout;
	@Inject
	private PageService pageService;
	int count = 1;

	public LayoutBean() {
	}

	@PostConstruct
	private void buildLayout() {
		FacesContext fc = FacesContext.getCurrentInstance();

		layout = buildPanel(fc);
		String friendlyUrl = (String) ((HttpServletRequest) fc.getExternalContext().getRequest()).getAttribute("path");
		Page page = pageService.getPage(friendlyUrl);
		for (LayoutRow row : page.getLayout().getRows()) {
			Dashboard dashboard = buildDashboard(fc, row);
			layout.getChildren().add(dashboard);
			Separator separator = (Separator) fc.getApplication().createComponent(fc, Separator.COMPONENT_TYPE,
					"org.primefaces.component.SeparatorRenderer");
			layout.getChildren().add(separator);
		}
	}

	private Panel buildPanel(FacesContext fc) {
		Application application = fc.getApplication();
		Panel panel = (Panel) application.createComponent(fc, Panel.COMPONENT_TYPE,
				"org.primefaces.component.PanelRenderer");
		return panel;
	}

	private Dashboard buildDashboard(FacesContext fc, LayoutRow row) {
		Application application = fc.getApplication();

		Dashboard dashboard = (Dashboard) application.createComponent(fc, Dashboard.COMPONENT_TYPE,
				"org.primefaces.component.DashboardRenderer");
		dashboard.setId("dashboard" + count++);
//		dashboard.setStyleClass("slot");
		

		DashboardModel model = new DefaultDashboardModel();
		for (LayoutColumn col : row.getColumns()) {
			DashboardColumn column = new DefaultDashboardColumn();
			for (WebletPreferences webletInstance : col.getWebletInstances()) {
				Panel panel = buildPanel(fc);
				panel.setId(webletInstance.getWebletId());
				panel.setHeader(webletInstance.getWebletId());
				panel.setClosable(true);
				panel.setToggleable(true);
				column.addWidget(panel.getId());
				dashboard.getChildren().add(panel);

				String viewId = "/weblet.xhtml";
				ViewHandler viewHandler = application.getViewHandler();
				UIViewRoot view = viewHandler.createView(fc, viewId);
				try {
					viewHandler.getViewDeclarationLanguage(fc, viewId).buildView(fc, view);
				} catch (IOException e) {
					e.printStackTrace();
				}

				panel.getChildren().add(view);
			}
			model.addColumn(column);
		}
		dashboard.setModel(model);
		return dashboard;
	}

	/**
	 * @return the layout
	 */
	public Panel getLayout() {
		return layout;
	}

	/**
	 * @param layout
	 *            the layout to set
	 */
	public void setLayout(Panel layout) {
		this.layout = layout;
	}
}
