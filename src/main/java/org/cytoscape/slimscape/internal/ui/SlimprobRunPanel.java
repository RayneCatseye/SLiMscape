package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.*;
import org.cytoscape.slimscape.internal.AlterGraph;
import org.cytoscape.slimscape.internal.CommonMethods;
import org.cytoscape.slimscape.internal.RunSlimprob;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @author: Kevin O'Brien
 */
public class SlimprobRunPanel extends JPanel {
    CyApplicationManager manager;
    JTextArea motifTextArea = null;
    JTextArea idTextArea = null;
    JTextArea uniprotTextArea = null;
    OpenBrowser openBrowser;
    SlimprobOptionsPanel optionsPanel;
    CyEventHelper eventHelper;
    CyNetworkFactory networkFactory;
    CyNetworkManager networkManager;
    CyNetworkViewFactory networkViewFactory;
    CyNetworkViewManager networkViewManager;
    VisualMappingManager visualMappingManager;
    JTabbedPane slimprob;

	/**
	 * Create the panel.
	 */
    public SlimprobRunPanel(final CyApplicationManager manager, final OpenBrowser openBrowser,
                            final SlimprobOptionsPanel optionsPanel, final CyEventHelper eventHelper,
                            final CyNetworkFactory networkFactory, final CyNetworkManager networkManager,
                            final CyNetworkViewFactory networkViewFactory, final CyNetworkViewManager networkViewManager,
                            final VisualMappingManager visualMappingManager, final JTabbedPane slimprob) {

        this.openBrowser = openBrowser;
        this.manager = manager;
        this.optionsPanel = optionsPanel;
        this.networkFactory = networkFactory;
        this.networkManager = networkManager;
        this.networkViewFactory = networkViewFactory;
        this.networkViewManager = networkViewManager;
        this.visualMappingManager = visualMappingManager;
        this.eventHelper = eventHelper;
        this.openBrowser = openBrowser;
        this.slimprob = slimprob;


        setBackground(new Color(238, 238, 238));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 558, 0 };
        gridBagLayout.rowHeights = new int[] { 208, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JPanel runSLiMFinderPanel = new JPanel();
        runSLiMFinderPanel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder("Run SLiMProb"),
                new EmptyBorder(0, 0, 0, 20)));
        GridBagLayout gbl_runSLiMFinderPanel = new GridBagLayout();
        gbl_runSLiMFinderPanel.columnWidths = new int[] { 466, 0 };
        gbl_runSLiMFinderPanel.rowHeights = new int[] { 25, 110, 0, 0, 0 };
        gbl_runSLiMFinderPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_runSLiMFinderPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        runSLiMFinderPanel.setLayout(gbl_runSLiMFinderPanel);
        GridBagConstraints gbc_runSLiMFinderPanel = new GridBagConstraints();
        gbc_runSLiMFinderPanel.fill = GridBagConstraints.BOTH;
        gbc_runSLiMFinderPanel.gridx = 0;
        gbc_runSLiMFinderPanel.gridy = 0;
        add(runSLiMFinderPanel, gbc_runSLiMFinderPanel);
        JButton runSlimprobButton = new JButton("Run SLiMProb");

        GridBagConstraints gbc_runSlimprobButton = new GridBagConstraints();
        gbc_runSlimprobButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_runSlimprobButton.insets = new Insets(0, 0, 5, 0);
        gbc_runSlimprobButton.gridx = 0;
        gbc_runSlimprobButton.gridy = 0;
        runSLiMFinderPanel.add(runSlimprobButton, gbc_runSlimprobButton);

        JPanel slimprobOptionsPanel = new JPanel();
        slimprobOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Parameters", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_slimprobOptionsPanel = new GridBagConstraints();
        gbc_slimprobOptionsPanel.insets = new Insets(0, 0, 5, 0);
        gbc_slimprobOptionsPanel.fill = GridBagConstraints.BOTH;
        gbc_slimprobOptionsPanel.gridx = 0;
        gbc_slimprobOptionsPanel.gridy = 1;
        runSLiMFinderPanel.add(slimprobOptionsPanel,
                gbc_slimprobOptionsPanel);
        GridBagLayout gbl_slimprobOptionsPanel = new GridBagLayout();
        gbl_slimprobOptionsPanel.columnWidths = new int[] { 0, 0 };
        gbl_slimprobOptionsPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_slimprobOptionsPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_slimprobOptionsPanel.rowWeights = new double[] { 1.0, 0.0, 1.0,
                Double.MIN_VALUE };
        slimprobOptionsPanel.setLayout(gbl_slimprobOptionsPanel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        slimprobOptionsPanel.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel motifLabel = new JLabel("Motifs:");
        GridBagConstraints gbc_motifLabel = new GridBagConstraints();
        gbc_motifLabel.anchor = GridBagConstraints.WEST;
        gbc_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc_motifLabel.gridx = 0;
        gbc_motifLabel.gridy = 1;
        slimprobOptionsPanel.add(motifLabel, gbc_motifLabel);

        motifTextArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 0, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 2;
        slimprobOptionsPanel.add(motifTextArea, gbc_textArea);

        JLabel idLabel = new JLabel("Run ID:");
        GridBagConstraints gbc1_motifLabel = new GridBagConstraints();
        gbc1_motifLabel.anchor = GridBagConstraints.WEST;
        gbc1_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc1_motifLabel.gridx = 0;
        gbc1_motifLabel.gridy = 3;
        slimprobOptionsPanel.add(idLabel, gbc1_motifLabel);

        JButton idCheckButton = new JButton("Check");
        GridBagConstraints gbc_checkLabel = new GridBagConstraints();
        gbc_checkLabel.anchor = GridBagConstraints.EAST;
        gbc_checkLabel.insets = new Insets(0, 0, 0, 5);
        gbc_checkLabel.gridx = 0;
        gbc_checkLabel.gridy = 3;
        slimprobOptionsPanel.add(idCheckButton, gbc_checkLabel);

        idTextArea = new JTextArea();
        GridBagConstraints gbc1_textArea = new GridBagConstraints();
        gbc1_textArea.insets = new Insets(0, 0, 0, 5);
        gbc1_textArea.fill = GridBagConstraints.BOTH;
        gbc1_textArea.gridx = 0;
        gbc1_textArea.gridy = 4;
        slimprobOptionsPanel.add(idTextArea, gbc1_textArea);

        JLabel uniprotLabel = new JLabel("Uniprot IDs:");
        gbc1_motifLabel = new GridBagConstraints();
        gbc1_motifLabel.anchor = GridBagConstraints.WEST;
        gbc1_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc1_motifLabel.gridx = 0;
        gbc1_motifLabel.gridy = 5;
        slimprobOptionsPanel.add(uniprotLabel, gbc1_motifLabel);

        uniprotTextArea = new JTextArea(4, 15);
        uniprotTextArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(uniprotTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setMinimumSize(new Dimension(15, 60));
        gbc1_textArea = new GridBagConstraints();
        gbc1_textArea.insets = new Insets(0, 0, 0, 5);
        gbc1_textArea.fill = GridBagConstraints.BOTH;
        gbc1_textArea.gridx = 0;
        gbc1_textArea.gridy = 6;
        slimprobOptionsPanel.add(scroll, gbc1_textArea);

        idCheckButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (idTextArea.getText().length() > 6) {
                    String id = idTextArea.getText();
                    int ready = CommonMethods.checkReady(id, openBrowser);
                    if (ready == 1) { // ready
                        resultProcessing(id);
                    } else {
                        JOptionPane.showMessageDialog(null, "This ID is still being processed. Please check back later.");
                    }
                }
            }
        });

        // Get selected nodes in the graph and send them for processing
        runSlimprobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                // There is a past runs ID in the box
                if (idTextArea.getText().length() > 6) {
                    // Send request to the server for that page
                    String id = idTextArea.getText();
                    resultProcessing(id);
                } else {
                    String motif = motifTextArea.getText();

                    // There are a set of IDs in the IDs box
                    if (uniprotTextArea.getText().length() > 5) {
                        String input = uniprotTextArea.getText();
                        // Strings have to be comma+space delineated ONLY
                        List<String> ids = Arrays.asList(input.split(",\\s+|\\s+"));
                        RunSlimprob slimprob = new RunSlimprob(network, null, ids, motif, optionsPanel);
                        String url = slimprob.getUrl();
                        String id = CommonMethods.getJobID(url).replaceAll("\\s+", "");
                        idTextArea.setText(id);
                        // Make sure the job is ready before analysis starts
                        int ready = CommonMethods.checkReady(id, openBrowser);
                        if (ready == 1) {
                            resultProcessing(id);
                        }
                        // Get node IDs from the graph
                    } else {
                        List<CyNode> selected = new ArrayList<CyNode>();
                        selected.addAll(CyTableUtil.getNodesInState(network, "selected", true));
                        if (selected.size() > 1) {
                            RunSlimprob slimprob = new RunSlimprob(network, selected, null, motif, optionsPanel);
                            String url = slimprob.getUrl();
                            String id = CommonMethods.getJobID(url).replaceAll("\\s+", "");
                            idTextArea.setText(id);
                            // Make sure the job is ready before analysis starts
                            int ready = CommonMethods.checkReady(id, openBrowser);
                            if (ready == 1) {
                                resultProcessing(id);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No nodes selected!");
                        }
                    }
                }
            }
        });
    }

    private void resultProcessing(String id) {
        try {
            List<String> csvResults = CommonMethods.PrepareResults(
                    "http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=main", openBrowser, id);
            if (csvResults != null) {
                displayResults(csvResults, id);
            } else {
                JOptionPane.showMessageDialog(null, "Unfortunately, there were no SLiMs found in your input.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Something went wrong! Either there are no SLiMs in your input, or a server error has occurred.");
        }
    }

    /**
     * @desc - creates the results panels and alters/creates the cytoscape network as required.
     * @param csvResults  - processed results from the main page.
     * @param id -  run ID from the server.
     */
    private void displayResults(List<String> csvResults, final String id) {
        // Get OCC Results
        List<String> occResults = CommonMethods.PrepareResults(
                "http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=occ", openBrowser, id);

        // Get list of all node IDs from slimdb
        List<String> nodeIds = CommonMethods.getNodeIds("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id
                + "&rest=slimdb");

        // Get graph edge data from upc outputs
        List<String> upc = CommonMethods.getUpcResults("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id
                + "&rest=upc");

        // Create button to take users to the full results
        JButton fullResults = new JButton();
        fullResults.setText("Full results");
        fullResults.setBorderPainted(false);
        fullResults.setOpaque(false);
        fullResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id);
            }
        });

        // Create button to take users to the help page on github
        JButton help = new JButton();
        help.setText("Help");
        help.setBorderPainted(false);
        help.setOpaque(false);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("https://github.com/RayneCatseye/SLiMscape/wiki/SLiMProb");
            }
        });


        JTable csv = slimprobCreateCsvTable(csvResults);
        JTable occ = CommonMethods.createOccTable(occResults);

        List<String> occIds = new ArrayList<String>();
        for (int y=0; y<occ.getRowCount(); y++) {
            Object current = occ.getModel().getValueAt(y, 1);
            String string = String.valueOf(current);
            occIds.add(string);
        }

        // Alter the graph
        new AlterGraph(nodeIds, occIds, upc, manager, eventHelper, networkFactory, networkManager,
                networkViewFactory, networkViewManager, visualMappingManager);

        // Display the results in a panel
        JPanel resultsPane = new ResultsPanel(new JScrollPane(csv), new JScrollPane(occ), fullResults, help, slimprob, id);
        slimprob.add("Run " + id + " Results", resultsPane);
    }

    /**
     * @desc Creates a csv-specific JTable from an input of comma separated strings.
     * @param input - a List<String> consisting of a series of comma-separated lines.
     * @return JTable - a table populated with the input elements.
     */
    public static JTable slimprobCreateCsvTable (List<String> input) {
        // Get column names from input
        JTable table;
        List<String> names = Arrays.asList(input.get(0).split(","));
            int s = names.size();
        List<String> abbreviated = new ArrayList<String>(names.subList(s-15, s-11));
        List<String> abbreviated1 = new ArrayList<String>(names.subList(s-8, s-7));
        List<String> abbreviated2 = new ArrayList<String>(names.subList(s-4, s));
        abbreviated.addAll(abbreviated1);
        abbreviated.addAll(abbreviated2);
        Object columnNames[] = new String[abbreviated.size()];
        abbreviated.toArray(columnNames);

        // Create table
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(400, 700));
        table.setFillsViewportHeight(true);

        // Add a row in table for each element in the input
        int lines = input.size();
        for(int c=1; c<lines; c++) {
            List<String> line = Arrays.asList(input.get(c).split(","));
            List<String> abbreviate = new ArrayList<String>(line.subList(s-15, s-11));
            List<String> abbreviate1 = new ArrayList<String>(line.subList(s-8, s-7));
            List<String> abbreviate2 = new ArrayList<String>(line.subList(s-4, s));
            abbreviate.addAll(abbreviate1);
            abbreviate.addAll(abbreviate2);
            Object lineObject[] = new String[abbreviate.size()];
            abbreviate.toArray(lineObject);
            model.addRow(lineObject);
        }

        // Table formatting
        table.setEnabled(false);
        TableColumn column;
        for (int i = 0; i < abbreviated.size(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setMinWidth(50);
        }
        return table;
    }

}