package br.com.adsdw.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.adsdw.model.Curso;
import br.com.adsdw.repository.Cursos;
import br.com.adsdw.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Curso.class)
public class CursoConverter implements Converter {

	//@Inject
	private Cursos cursos;
	
	public CursoConverter() {
		cursos = CDIServiceLocator.getBean(Cursos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Curso retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = cursos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		try {
		if (value != null) {
			return ((Curso) value).getId().toString();
		}
		
		} catch (NullPointerException e) {
			System.out.print("erro:" + e);
		}
		return "";
	}

}
