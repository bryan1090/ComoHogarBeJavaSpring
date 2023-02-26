package com.comoHogar.sistemaRegistro.infraetructure.outputAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.comoHogar.sistemaRegistro.domain.Beneficio;
import com.comoHogar.sistemaRegistro.domain.Client;
import com.comoHogar.sistemaRegistro.domain.ClientId;
import com.comoHogar.sistemaRegistro.domain.SkDTO;
import com.comoHogar.sistemaRegistro.infraetructure.inputPort.ClientInputPort;
import com.comoHogar.sistemaRegistro.infraetructure.outputPort.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClientService implements ClientInputPort {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client guardar(Client client) {
		Client c = new Client();
		if (client.getGrupo().equalsIgnoreCase("th")) {
			List<String> listaBeneficios = this.leerXml();
			if (listaBeneficios.isEmpty()) {
				return null;
			}
			client.setBeneficio(listaBeneficios.get(0));
			c = this.clientRepository.save(client);
		}else
		{
			List<String> listaBeneficios = this.leerJSON();
			if (listaBeneficios.isEmpty()) {
				return null;
			}
			client.setBeneficio(listaBeneficios.get(0));
			c = this.clientRepository.save(client);
		}
		return c;
	}

	@Override
	public List<Client> listar() {
		return this.clientRepository.findAll();
	}

	public List<String> leerXml() {
		List<String> lista = new ArrayList<>();
		URL url;
		try {
			url = new URL(
					"https://raw.githubusercontent.com/SistemasComoHogar/ClientesBack/604e5db393d54968882d2775fa8f6059a58cce40/Referencias/th_formato.xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print in String
			// System.out.println(response.toString());
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(response.toString())));
			NodeList nList = doc.getElementsByTagName("beneficios");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				System.out.println("" + nNode.getNodeName());

				NodeList nList2 = ((Element) nNode).getElementsByTagName("beneficio");
				for (int j = 0; j < nList2.getLength(); j++) {
					Element elem = (Element) nNode;
					Node node2 = elem.getElementsByTagName("beneficio").item(j);
					String opcion = node2.getTextContent();
					System.out.printf("Beneficio: %s%n", opcion);
					lista.add(opcion);
				}
			}

			con.disconnect();

		} catch (

		IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<String> leerJSON() {
		List<String> lista = new ArrayList<>();
		URL url;
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		

		try {
			url = new URL(
					"https://raw.githubusercontent.com/SistemasComoHogar/ClientesBack/604e5db393d54968882d2775fa8f6059a58cce40/Referencias/sk_formato.json");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			ObjectMapper mapper = new ObjectMapper();

			SkDTO obj = mapper.readValue((url), SkDTO.class);
			
			for (Beneficio d : obj.getSk_formato()) {
				lista.add(d.getBeneficio());
			}

			con.disconnect();

		} catch (

		IOException e) {
			e.printStackTrace();
		} 
		return lista;
	}
	

}
