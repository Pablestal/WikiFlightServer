package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.udc.lbd.asi.restexample.CustomGeometryDeserializer;
import es.udc.lbd.asi.restexample.CustomGeometrySerializer;
import es.udc.lbd.asi.restexample.model.domain.Aerodrome;

public class AerodromeDTO {

		private Long id;
		
		private String codIATA;
		
		private String codOACI;
		
		@NotEmpty
		private String name;
		
		@NotEmpty
		private String country;
		
		@NotEmpty	
		private String city;
		
		private Double elevation;
		
		@NotEmpty
		@JsonSerialize(using = CustomGeometrySerializer.class, as= Point.class)
		@JsonDeserialize(using = CustomGeometryDeserializer.class, as= Point.class)
		private Point position;
		
		public AerodromeDTO() {
			
		}
		
		public AerodromeDTO(Aerodrome aerodrome) {
			this.id = aerodrome.getId();
			this.codIATA = aerodrome.getCodIATA();
			this.codOACI = aerodrome.getCodOACI();
			this.name = aerodrome.getName();
			this.country = aerodrome.getCountry();
			this.city = aerodrome.getCity();
			this.elevation = aerodrome.getElevation();
			this.position = aerodrome.getPosition();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCodIATA() {
			return codIATA;
		}

		public void setCodIATA(String codIATA) {
			this.codIATA = codIATA;
		}

		public String getCodOACI() {
			return codOACI;
		}

		public void setCodOACI(String codOACI) {
			this.codOACI = codOACI;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public Double getElevation() {
			return elevation;
		}

		public void setElevation(Double elevation) {
			this.elevation = elevation;
		}

		public Point getPosition() {
			return position;
		}

		public void setPosition(Point position) {
			this.position = position;
		}

		@Override
		public String toString() {
			return "AerodromeDTO [id=" + id + ", codIATA=" + codIATA + ", codOACI=" + codOACI + ", name=" + name
					+ ", country=" + country + ", city=" + city + ", elevation=" + elevation + ", position=" + position
					+ "]";
		}
		
		
}

	
