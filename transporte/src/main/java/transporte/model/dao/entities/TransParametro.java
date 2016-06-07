package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trans_parametros database table.
 * 
 */
@Entity
@Table(name="trans_parametros")
@NamedQuery(name="TransParametro.findAll", query="SELECT t FROM TransParametro t")
public class TransParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="par_id", length=30)
	private String parId;

	@Column(name="par_nombre", length=50)
	private String parNombre;

	@Column(name="par_valor")
	private String parValor;

	public TransParametro() {
	}

	public String getParId() {
		return this.parId;
	}

	public void setParId(String parId) {
		this.parId = parId;
	}

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public String getParValor() {
		return this.parValor;
	}

	public void setParValor(String parValor) {
		this.parValor = parValor;
	}

}