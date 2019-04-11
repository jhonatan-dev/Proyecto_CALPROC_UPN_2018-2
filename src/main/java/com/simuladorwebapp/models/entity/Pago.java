package com.simuladorwebapp.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pago")
public class Pago implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String codigo;
	
	@Column(nullable=false)
	private double importe;
	
	@Column(nullable=false)
	private double monto_cuota;
	
	@Column(nullable=false)
	private double monto_mora;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(nullable=false)
	private Date fecha_pago;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable=false, foreignKey = @ForeignKey(name="FK_PG_USUARIO_ID"))
	private Usuario usuario;
	
	@OneToOne(mappedBy = "pago", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Cuota cuota;
	
	public Pago() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getMonto_cuota() {
		return monto_cuota;
	}

	public void setMonto_cuota(double monto_cuota) {
		this.monto_cuota = monto_cuota;
	}

	public double getMonto_mora() {
		return monto_mora;
	}

	public void setMonto_mora(double monto_mora) {
		this.monto_mora = monto_mora;
	}

	public Date getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cuota getCuota() {
		return cuota;
	}

	public void setCuota(Cuota cuota) {
		if (cuota == null) {
            if (this.cuota != null) {
                this.cuota.setPago(null);
            }
        }
        else {
            cuota.setPago(this);
        }
		this.cuota = cuota;
	}

	private int obtenerDiasDeDiferencia(Date fechaInicial, Date fechaFinal) {
	    int diferencia = 0;
	    long diff =  Math.abs(fechaFinal.getTime() - fechaInicial.getTime());
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    diferencia = (int) diffDays;
	    return diferencia;
	}
	
	private String generarCodigoPago() {
        String s = "";
		double d;
		for (int i = 1; i <= 16; i++) {
            d = Math.random() * 10;
            s = s + ((int)d);
            if (i % 4 == 0 && i != 16) {
                s = s + "-";
            }
        }
		return s;
	}
	
	public void PagarCuota(Cuota cuotaExtern) {
		codigo = generarCodigoPago();
		fecha_pago = new Date();
		monto_cuota = cuotaExtern.getCuota_fija();
		monto_mora = 0;
		if(fecha_pago.after(cuotaExtern.getFecha_vencimiento())) {
			monto_mora = cuotaExtern.getPip() * obtenerDiasDeDiferencia(cuotaExtern.getFecha_vencimiento(), fecha_pago);
		}
        importe = monto_cuota + monto_mora;
	}
	
}
