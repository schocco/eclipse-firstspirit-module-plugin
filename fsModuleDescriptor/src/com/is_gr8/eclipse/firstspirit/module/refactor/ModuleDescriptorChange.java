package com.is_gr8.eclipse.firstspirit.module.refactor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModuleDescriptorChange extends Change {

	private IFile moduleDescriptor;
	private String oldName;
	private String newName;
	private boolean domChanged;

	public ModuleDescriptorChange(IFile moduleDescriptor, String oldName, String newName) {
		this.moduleDescriptor = moduleDescriptor;
		this.oldName = oldName;
		this.newName = newName;
	}

	@Override
	public Object getModifiedElement() {
		return moduleDescriptor;
	}

	@Override
	public String getName() {
		return "Module Change";
	}

	@Override
	public void initializeValidationData(IProgressMonitor arg0) {

	}

	@Override
	public RefactoringStatus isValid(IProgressMonitor mon) throws CoreException, OperationCanceledException {
		return new RefactoringStatus();
	}

	@Override
	public Change perform(IProgressMonitor monitor) throws CoreException {
		try (InputStream is = moduleDescriptor.getContents()) {
			Document doc = loadDocument(is);
			XPathFactory xpf = XPathFactory.newInstance();
	        XPath xpath = xpf.newXPath();
	        NodeList classNodes = (NodeList) xpath.evaluate("//class|//configurable", doc, XPathConstants.NODESET);
			replaceTypeOccurences(classNodes);
			if(domChanged) {
				InputStream resultIs = documentToInputStream(doc);
				moduleDescriptor.setContents(resultIs, false, true, monitor);
				return getRevertChange();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Document loadDocument(InputStream inputStream)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// ignore dtd which points to non-existing file in most module.xmls
		dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream);
		return doc;
	}

	private void replaceTypeOccurences(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			if (item.getTextContent().equals(oldName)) {
				item.setTextContent(newName);
				domChanged = true;
			}
		}
	}

	private InputStream documentToInputStream(Document doc) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
	private ModuleDescriptorChange getRevertChange() {
		return new ModuleDescriptorChange(moduleDescriptor, newName, oldName);
	}

}
