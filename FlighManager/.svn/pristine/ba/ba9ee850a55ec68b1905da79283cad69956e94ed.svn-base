package edu.msg.flightmanager.web.beans;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;
import org.w3c.dom.Document;

import edu.msg.flightmanager.backend.dto.FlightDto;

@ManagedBean
@ViewScoped
public class AddMarkersView implements Serializable {

	private static final long serialVersionUID = 8280803590434215840L;

	private MapModel emptyModel;

	private FlightDto flightDto;

	private String center;

	private String zoom;

	public FlightDto getFlightDto() {
		return flightDto;
	}

	public void setFlightDto(FlightDto flightDto) {
		this.flightDto = flightDto;
	}

	public void addMarkers() {
		try {
			flightDto.getAircrewUsers();
			emptyModel = new DefaultMapModel();
			String departureCity = flightDto.getTemplate().getDeparture().getCity();
			String departureAirport = flightDto.getTemplate().getDeparture().getName();
			String arrivalCity = flightDto.getTemplate().getArrival().getCity();
			String arrivalAirport = flightDto.getTemplate().getArrival().getName();
			String[] departureCoords = getLatLongPositions(departureCity);
			LatLng departure = new LatLng(Double.parseDouble(departureCoords[0]),
					Double.parseDouble(departureCoords[1]));
			String[] arrivalCoords = getLatLongPositions(arrivalCity);
			LatLng arrival = new LatLng(Double.parseDouble(arrivalCoords[0]), Double.parseDouble(arrivalCoords[1]));
			emptyModel.addOverlay(new Marker(departure, departureAirport));
			emptyModel.addOverlay(new Marker(arrival, arrivalAirport));
			Double centerLat = (departure.getLat() + arrival.getLat()) / 2;
			String centerLatString = centerLat.toString();
			Double centerLong = (departure.getLng() + arrival.getLng()) / 2;
			String centerLongString = centerLong.toString();
			setCenter(centerLatString + "," + centerLongString);
			Polyline polyline = new Polyline();
			polyline.getPaths().add(arrival);
			polyline.getPaths().add(departure);
			polyline.setStrokeWeight(10);
			polyline.setStrokeColor("#FFFF00");
			polyline.setStrokeOpacity(0.6);
			setZoom("4");
			emptyModel.addOverlay(polyline);
			setModel(emptyModel);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public MapModel getModel() {
		return emptyModel;
	}

	public void setModel(MapModel model) {
		this.emptyModel = model;
	}

	public static String[] getLatLongPositions(String address) throws Exception {
		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
				+ "&sensor=true";
		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();
		responseCode = httpConnection.getResponseCode();
		if (responseCode == 200) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			;
			Document document = builder.parse(httpConnection.getInputStream());
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/GeocodeResponse/status");
			String status = (String) expr.evaluate(document, XPathConstants.STRING);
			if (status.equals("OK")) {
				expr = xpath.compile("//geometry/location/lat");
				String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
				expr = xpath.compile("//geometry/location/lng");
				String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
				return new String[] { latitude, longitude };
			} else {
				throw new Exception("Error from the API - response status: " + status);
			}
		}
		return null;

	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

}
