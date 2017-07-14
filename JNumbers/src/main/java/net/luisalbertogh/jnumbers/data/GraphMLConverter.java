/**
 * 
 */
package net.luisalbertogh.jnumbers.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * @author lagarcia
 */
public class GraphMLConverter {

    /**
     * Get Graph XML file with number & dividers.
     * 
     * @param maxNumber
     * @throws Exception
     */
    public void getGraphMLFile(int maxNumber) throws Exception {
        // GraphML xml file
        Document doc = new Document();
        // Root
        Element rootNode = new Element("graphml");
        rootNode.setNamespace(Namespace.getNamespace("http://graphml.graphdrawing.org/xmlns"));
        doc.setRootElement(rootNode);
        // Graph
        Element graphNode = new Element("graph");
        graphNode.setAttribute("edgedefault", "undirected");
        rootNode.addContent(graphNode);
        // Keys
        // Value
        Element keyNode = new Element("key");
        keyNode.setAttribute("id", "value");
        keyNode.setAttribute("for", "node");
        keyNode.setAttribute("attr.name", "value");
        keyNode.setAttribute("attr.type", "string");
        graphNode.addContent(keyNode);
        // Divided by
        keyNode = new Element("key");
        keyNode.setAttribute("id", "dividers");
        keyNode.setAttribute("for", "node");
        keyNode.setAttribute("attr.name", "dividers");
        keyNode.setAttribute("attr.type", "string");
        graphNode.addContent(keyNode);

        /** Add nodes and edges */
        for (int i = 1; i <= maxNumber; i++) {
            String dividers = addNode(i, graphNode);
            addEdges(i, dividers, graphNode);
        }

        // Write XML file
        Writer w = new FileWriter("data/output.xml");
        XMLOutputter outputter = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setEncoding("ISO-8859-1");
        outputter.setFormat(format);
        outputter.output(doc, w);
        w.flush();
        w.close();
    }

    private String addNode(int value, Element graphNode) {
        // Parent file
        Element nodeNode = new Element("node");
        nodeNode.setAttribute("id", String.valueOf(value));

        // Value
        Element dataNode = new Element("data");
        dataNode.setAttribute("key", "value");
        dataNode.setText(String.valueOf(value));
        nodeNode.addContent(dataNode);

        // Dividers
        dataNode = new Element("data");
        dataNode.setAttribute("key", "dividers");

        String dividers = "";
        for (int i = value; i > 0; i--) {
            /** It can be divided */
            if (value % i == 0) {
                dividers += i + ",";
            }
        }
        dataNode.setText(dividers);

        nodeNode.addContent(dataNode);

        graphNode.addContent(nodeNode);

        return dividers;
    }

    private void addEdges(int value, String dividers, Element graphNode) {
        StringTokenizer st = new StringTokenizer(dividers, ",");
        while (st.hasMoreTokens()) {
            String divider = st.nextToken();

            /** Edge */
            Element edgeNode = new Element("edge");
            edgeNode.setAttribute("source", String.valueOf(value));
            edgeNode.setAttribute("target", divider);
            graphNode.addContent(edgeNode);
        }
    }

    /**
     * Generate the XML file that contains the data structure as a tree map.
     * 
     * @param filepath Path file to XML.
     * @throws Exception
     */
    public void generateTreeMap(String filepath) throws Exception {
        Element root = new Element("tree");

        // Data attributes
        Element declarations = new Element("declarations");
        // Name
        Element attributeDecl = new Element("attributeDecl");
        attributeDecl.setAttribute("name", "name");
        attributeDecl.setAttribute("type", "String");
        declarations.addContent(attributeDecl);
        // Folder
        attributeDecl = new Element("attributeDecl");
        attributeDecl.setAttribute("name", "folder");
        attributeDecl.setAttribute("type", "String");
        declarations.addContent(attributeDecl);
        // Path
        attributeDecl = new Element("attributeDecl");
        attributeDecl.setAttribute("name", "path");
        attributeDecl.setAttribute("type", "String");
        declarations.addContent(attributeDecl);

        root.addContent(declarations);

        Element mainBranch = new Element("branch");
        Element attributeMainBranch = new Element("attribute");
        attributeMainBranch.setAttribute("name", "name");
        attributeMainBranch.setAttribute("value", new File(filepath).getName());
        mainBranch.addContent(attributeMainBranch);
        root.addContent(mainBranch);

        // Root
        addNodeTree(new File(filepath), mainBranch, 1);

        // Write XML doc
        Document doc = new Document(root);
        XMLOutputter outputter = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setEncoding("ISO-8859-1");
        outputter.setFormat(format);
        outputter.output(doc, new FileOutputStream("data/treemap.xml"));
    }

    private void addNodeTree(File parentFile, Element rootNode, int level) {
        // Children
        File[] children = parentFile.listFiles();

        // File or empty directory
        if (children == null) {
            return;
        }

        for (int i = 0; i < children.length; i++) {
            // File or empty folder
            if (children[i].isFile() || (children[i].isDirectory() && children[i].listFiles().length == 0)) {
                Element nodeLeaf = new Element("leaf");
                // Name
                Element nodeAttribute = new Element("attribute");
                nodeAttribute.setAttribute("name", "name");
                nodeAttribute.setAttribute("value", children[i].getName());
                nodeLeaf.addContent(nodeAttribute);
                // Path
                nodeAttribute = new Element("attribute");
                nodeAttribute.setAttribute("name", "path");
                nodeAttribute.setAttribute("value", children[i].getAbsolutePath());
                nodeLeaf.addContent(nodeAttribute);
                // Folder (only first level)
                File child = children[i];
                for (int j = level; j > 1; j--) {
                    child = child.getParentFile();
                }
                String parentFolder = child.getAbsolutePath();

                nodeAttribute = new Element("attribute");
                nodeAttribute.setAttribute("name", "folder");
                nodeAttribute.setAttribute("value", parentFolder);
                nodeLeaf.addContent(nodeAttribute);

                rootNode.addContent(nodeLeaf);
            } else {
                // Folder branch
                Element folderBranch = new Element("branch");
                // Name
                Element attribute = new Element("attribute");
                attribute.setAttribute("name", "name");
                attribute.setAttribute("value", children[i].getName());
                folderBranch.addContent(attribute);
                rootNode.addContent(folderBranch);

                // Recursive
                addNodeTree(children[i], folderBranch, level + 1);
            }
        }
    }
}
