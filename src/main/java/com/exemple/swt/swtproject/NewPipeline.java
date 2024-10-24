package com.exemple.swt.swtproject;

import java.awt.SecondaryLoop;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class NewPipeline {

	private static Button radioASCIIDirectional;
    private static Button radioASCIILithology;
    private static Button radioASCIILogCurves;
    private static Button radioASCIIDrillingParameters;
    private static Button radioWitsmlDirectional;
    private static Button radioWitsmlLogCurves;
    private static Button radioExataDirectional;
    private static Button radioExataLithology;
    private static Button radioExataLogCurves;
    private static Button radioExataDrillingParameters;
    private static TabFolder tabFolder;
    private static TabItem directionalTab;
    private static TabItem lithologyTab;
    private static TabItem logCurvesTab;
    private static TabItem drillingParametersTab;
    
    private static Combo connectionTypeCombo;
    private static Text pipelineNameText;
    private static Text uwiText;
    private static Combo uwiSourceCombo;
    private static Combo connectionCombo;
    
    private static Button enableASCIIDirecional;
    private static Text inputFileTextASCIIDirecional;
    private static Text surveyNameTextASCIIDirecional;
    private static Button enableASCIILithology;
    private static Text inputFileTextASCIILithology;
    private static Combo curveTypeComboASCIILithology;
    private static Button enableASCIILogCurves;
    private static Text inputFileTextASCIILogCurves;
    private static Button enableASCIIDrillingParameters;
    private static Text inputFileTextASCIIDrilling;
    private static Button enableWitsmlDirecional;
    private static Text surveyNameTextWitsmlDirecional;
    private static Button enableWitsmlLogCurves;
    private static Combo logServiceComboWitsmlLogCurves;
    private static Combo mnemonicDepthComboWitsmlLogCurves;
    private static Button enableExataDirecional;
    private static Text surveyNameTextExataDirecional;
    private static Button enableExataLithology;
    private static Combo curveTypeComboExataLithology;
    private static Button enableExataLogCurves;
    private static Combo logServiceComboExataLogCurves;
    private static Combo mnemonicDepthComboExataLogCurves;
    private static Button enableExataDrillingParameters;
    
    public void createPartControl(Composite firstPanel) {
    	// Connection Type, Pipeline Name, UWI
        //Composite firstPanel = new Composite(shell, SWT.NONE);
        firstPanel.setLayout(new GridLayout(5, false));
        firstPanel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Dropdown Connection Type
        Label connectionTypeLabel = new Label(firstPanel, SWT.NONE);
        connectionTypeLabel.setText("Connection type:");
        
        connectionTypeCombo = new Combo(firstPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
        connectionTypeCombo.setItems(new String[] { "ASCII", "WITSML", "VDB" });
        connectionTypeCombo.select(0);
        
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);

        // Pipeline Name
        Label pipelineNameLabel = new Label(firstPanel, SWT.NONE);
        pipelineNameLabel.setText("Pipeline Name:");
        
        pipelineNameText = new Text(firstPanel, SWT.BORDER);
        pipelineNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        
        Label connectionLabel = new Label(firstPanel, SWT.NONE);
    	connectionLabel.setText("Connection:");
    	connectionCombo = new Combo(firstPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
    	connectionCombo.setItems(new String[] { "" });
    	connectionCombo.select(0);
    	connectionCombo.setEnabled(false);
    	GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 1;
        connectionCombo.setLayoutData(gridData1);

        Button browseButton = new Button(firstPanel, SWT.PUSH);
        browseButton.setText("Browse");
        browseButton.setEnabled(false);
        
        browseButton.addListener(SWT.Selection, event -> {
            
        });
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        
        Label uwiSourceLabel = new Label(firstPanel, SWT.NONE);
        uwiSourceLabel.setText("UWI Source:");
        uwiSourceCombo = new Combo(firstPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
        uwiSourceCombo.setItems(new String[] { "" });
        uwiSourceCombo.select(0);
        uwiSourceCombo.setEnabled(false);
        uwiSourceCombo.setLayoutData(gridData1);
        
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        
        connectionTypeCombo.addListener(SWT.Selection, event -> {
        	if (connectionTypeCombo.getText().equals("WITSML")) {
        		connectionCombo.setEnabled(true);
        		browseButton.setEnabled(true);
        		uwiSourceCombo.setEnabled(true);
        	} else if (connectionTypeCombo.getText().equals("VDB")) {
        		connectionCombo.setEnabled(false);
        		browseButton.setEnabled(false);
        		uwiSourceCombo.setEnabled(true);
        	}
        	else {
        		connectionCombo.setEnabled(false);
        		browseButton.setEnabled(false);
        		uwiSourceCombo.setEnabled(false);
        	}
        });

        // UWI + Browse
        Label uwiLabel = new Label(firstPanel, SWT.NONE);
        uwiLabel.setText("UWI:");
        
        uwiText = new Text(firstPanel, SWT.BORDER | SWT.READ_ONLY);
        uwiText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Button browseButton2 = new Button(firstPanel, SWT.PUSH);
        browseButton2.setText("Browse");
        
        browseButton2.addListener(SWT.Selection, event -> {
            FileDialog fileDialog = new FileDialog((Shell) firstPanel, SWT.OPEN);
            fileDialog.setText("Select File");
            fileDialog.setFilterPath("C:/");
            fileDialog.setFilterExtensions(new String[] { "*.txt", "*.*" });

            String selectedFile = fileDialog.open();
            if (selectedFile != null) {
                uwiText.setText(selectedFile);
            }
        });
        
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 5;
        Label space = new Label(firstPanel, SWT.NONE);
        space.setLayoutData(gridData2);

        // Tabs (Directional, Lithology, Log Curves, Drilling Parameters)
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 2;
        Composite secondPanel = new Composite(firstPanel, SWT.NONE);
        secondPanel.setLayout(new FillLayout());
        secondPanel.setLayoutData(gridData);

        tabFolder = new TabFolder(secondPanel, SWT.BORDER); 

        createDirectionalTab(firstPanel, 0); 
        createLithologyTab(firstPanel, 0);
        createLogCurvesTab(firstPanel, 0);
        createDrillingParametersTab(firstPanel, 0);
        
        connectionTypeCombo.addListener(SWT.Selection, event -> {
        	disposeAllTabs();
        	String selectedConnection = connectionTypeCombo.getText();
        	
        	switch (selectedConnection) {
	            case "ASCII":
	                createDirectionalTab(firstPanel, 0); 
	                createLithologyTab(firstPanel, 0);
	                createLogCurvesTab(firstPanel, 0);
	                createDrillingParametersTab(firstPanel, 0);
	                break;
	            case "WITSML":
	                createDirectionalTab(firstPanel, 1); 
	                createLogCurvesTab(firstPanel, 1);
	                break;
	            case "VDB":
	                createDirectionalTab(firstPanel, 2); 
	                createLithologyTab(firstPanel, 2);
	                createLogCurvesTab(firstPanel, 2);
	                createDrillingParametersTab(firstPanel, 2);
	                break;
	        }
        	
        	tabFolder.layout();
        });
        
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
        new Label(firstPanel, SWT.NONE);
                
        // Radio Buttons Listeners
        addRadioListeners();

        // Save and Cancel
        Composite thirdPanel = new Composite(firstPanel, SWT.NONE);
        thirdPanel.setLayout(new GridLayout(2, true));
        thirdPanel.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, false));
        
        Button saveButton = new Button(thirdPanel, SWT.PUSH);
        saveButton.setText("Save");
        
        saveButton.addListener(SWT.Selection, event -> {
            if (validateRequiredFields()) {
                MessageBox dialog = new MessageBox((Shell) firstPanel, SWT.ICON_INFORMATION | SWT.OK);
                dialog.setText("Information");
                dialog.setMessage("Saved Successfully!");
                dialog.open();
                firstPanel.dispose();
            }
        });

        Button cancelButton = new Button(thirdPanel, SWT.PUSH);
        cancelButton.setText("Cancel");
        
        cancelButton.addListener(SWT.Selection, event -> {
        	firstPanel.dispose();
        });	
	}
    
    public NewPipeline() {
        
    }
    
    private void disposeAllTabs() {
        if (directionalTab != null && !directionalTab.isDisposed()) {
            directionalTab.dispose();
        }
        if (lithologyTab != null && !lithologyTab.isDisposed()) {
            lithologyTab.dispose();
        }
        if (logCurvesTab != null && !logCurvesTab.isDisposed()) {
            logCurvesTab.dispose();
        }
        if (drillingParametersTab != null && !drillingParametersTab.isDisposed()) {
            drillingParametersTab.dispose();
        }
    }
    
    private void createDirectionalTab(Composite shell, int type) {
    	// Directional Tab
        directionalTab = new TabItem(tabFolder, SWT.NONE);
        directionalTab.setText("Directional");

        Composite directionalComposite = new Composite(tabFolder, SWT.NONE);
        GridLayout grid = new GridLayout(5, true);
        grid.verticalSpacing = 10;
        directionalComposite.setLayout(grid);
        directionalTab.setControl(directionalComposite);

        if (type == 0) {
        	createASCIIDirecionalFields(shell, directionalComposite);
        }
        else if (type == 1) {
        	createWitsmlDirecionalFields(directionalComposite);
        }
        else {
        	createExataDirecionalFields(directionalComposite);
        }
        
        directionalComposite.pack();
        directionalComposite.redraw();
        directionalComposite.layout();
    }
    
    private void createLithologyTab(Composite shell, int type) {
    	// Lithology Tab
        lithologyTab = new TabItem(tabFolder, SWT.NONE);
        lithologyTab.setText("Lithology");

        Composite lithologyComposite = new Composite(tabFolder, SWT.NONE);
        GridLayout grid = new GridLayout(5, true);
        grid.verticalSpacing = 10;
        lithologyComposite.setLayout(grid);
        lithologyTab.setControl(lithologyComposite);

        if (type == 0) {
        	createASCIILithology(shell, lithologyComposite);
        }
        else {
        	createExataLithology(shell, lithologyComposite);
        }
        lithologyComposite.pack();
        lithologyComposite.redraw();
    }
    
    private void createLogCurvesTab(Composite shell, int type) {
    	// Log Curves Tab
        logCurvesTab = new TabItem(tabFolder, SWT.NONE);
        logCurvesTab.setText("Log Curves");

        Composite logCurvesComposite = new Composite(tabFolder, SWT.NONE);
        GridLayout grid = new GridLayout(5, true);
        grid.verticalSpacing = 10;
        logCurvesComposite.setLayout(grid);
        logCurvesTab.setControl(logCurvesComposite);

        if (type == 0) {
        	createASCIILogCurves(shell, logCurvesComposite);
        }
        else if (type == 1) {
        	createWitsmlLogCurves(logCurvesComposite);
        }        
        else {
        	createExataLogCurves(logCurvesComposite);
        }
        logCurvesComposite.pack();
        logCurvesComposite.redraw();
    }
    
    private void createDrillingParametersTab(Composite shell, int type) {
    	// Drilling Parameters Tab
        drillingParametersTab = new TabItem(tabFolder, SWT.NONE);
        drillingParametersTab.setText("Drilling Parameters");

        Composite drillingComposite = new Composite(tabFolder, SWT.NONE);
        GridLayout grid = new GridLayout(5, true);
        grid.verticalSpacing = 10;
        drillingComposite.setLayout(grid);
        drillingParametersTab.setControl(drillingComposite);

        if (type == 0) {
        	createASCIIDrillingParameters(shell, drillingComposite);
        }
        else {
        	createExataDrillingParameters(shell, drillingComposite);
        }        
        drillingComposite.pack();
        drillingComposite.redraw();
    }
    
    private void addRadioListeners() {
        Listener radioListener = event -> {
            radioASCIIDirectional.setSelection(false);
            radioASCIILithology.setSelection(false);
            radioASCIILogCurves.setSelection(false);
            radioASCIIDrillingParameters.setSelection(false);

            ((Button) event.widget).setSelection(true);
        };

        radioASCIIDirectional.addListener(SWT.Selection, radioListener);
        radioASCIILithology.addListener(SWT.Selection, radioListener);
        radioASCIILogCurves.addListener(SWT.Selection, radioListener);
        radioASCIIDrillingParameters.addListener(SWT.Selection, radioListener);
    }
    
    private void createASCIIDirecionalFields(Composite shell, Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableASCIIDirecional = new Button(composite, SWT.CHECK);
    	enableASCIIDirecional.setText("Enable Directional Update");
    	enableASCIIDirecional.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableASCIIDirecional.setLayoutData(gridData);
    	
    	Button overwriteASCIIDirecional = new Button(composite, SWT.CHECK);
    	overwriteASCIIDirecional.setEnabled(false);
    	overwriteASCIIDirecional.setText("Overwrite");
    	
    	radioASCIIDirectional = new Button(composite, SWT.RADIO);
        radioASCIIDirectional.setText("Use as Total Depth");
        radioASCIIDirectional.setEnabled(false);
        
        Label inputFileLabel = new Label(composite, SWT.NONE);
    	inputFileLabel.setText("Input File:");
        inputFileTextASCIIDirecional = new Text(composite, SWT.BORDER);
        inputFileTextASCIIDirecional.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        inputFileTextASCIIDirecional.setEnabled(false);
        GridData gridData0 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData0.horizontalSpan = 3;
        inputFileTextASCIIDirecional.setLayoutData(gridData0);

        Button browseButton = new Button(composite, SWT.PUSH);
        browseButton.setText("Browse");
        browseButton.setEnabled(false);
        
        browseButton.addListener(SWT.Selection, event -> {
            FileDialog fileDialog = new FileDialog((Shell) shell, SWT.OPEN);
            fileDialog.setText("Select File");
            fileDialog.setFilterPath("C:/");
            fileDialog.setFilterExtensions(new String[] { "*.txt", "*.*" });

            String selectedFile = fileDialog.open();
            if (selectedFile != null) {
            	inputFileTextASCIIDirecional.setText(selectedFile);
            }
        });
        
        Label surveyCalcLabel = new Label(composite, SWT.NONE);
        surveyCalcLabel.setText("Survey Calc Mthd:");
        Combo surveyCalcText = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        surveyCalcText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        surveyCalcText.setItems(new String[] { "Angle Averaging", "Balanced Tangential", "Minimum Curvature", "Radius of Curvature", "Tangential", "Unknown" });
        surveyCalcText.select(2);
        surveyCalcText.setEnabled(false);
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 2;
        surveyCalcText.setLayoutData(gridData2);
        Label surveyCalcLabel2 = new Label(composite, SWT.NONE);
        surveyCalcLabel2.setText("North Reference:");;
        Combo referenceCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        referenceCombo.setItems(new String[] { "Unknown", "Grid", "True" });
        referenceCombo.select(1);
        referenceCombo.setEnabled(false);
        
        Label surveyNameLabel = new Label(composite, SWT.NONE);
        surveyNameLabel.setText("Survey Name:");
        surveyNameTextASCIIDirecional = new Text(composite, SWT.BORDER);
        surveyNameTextASCIIDirecional.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        surveyNameTextASCIIDirecional.setEnabled(false);
        GridData gridData4 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData4.horizontalSpan = 3;
        surveyNameTextASCIIDirecional.setLayoutData(gridData4);
        
        Button selectButton = new Button(composite, SWT.PUSH);
        selectButton.setText("Select...");
        selectButton.setEnabled(false);

        Label remarksLabel = new Label(composite, SWT.NONE);
        remarksLabel.setText("Remarks:");
        Text remarksText = new Text(composite, SWT.BORDER);
        remarksText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        remarksText.setEnabled(false);
        GridData gridData3 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData3.horizontalSpan = 4;
        remarksText.setLayoutData(gridData3);
        
        enableASCIIDirecional.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			radioASCIIDirectional.setEnabled(true);
    			inputFileTextASCIIDirecional.setEnabled(true);
    			browseButton.setEnabled(true);
    			surveyCalcText.setEnabled(true);
    			surveyNameTextASCIIDirecional.setEnabled(true);
    			selectButton.setEnabled(true);
    			referenceCombo.setEnabled(true);
    			remarksText.setEnabled(true);
    			overwriteASCIIDirecional.setEnabled(true);
    		}
    		else {
    			radioASCIIDirectional.setEnabled(false);
    			inputFileTextASCIIDirecional.setEnabled(false);
    			browseButton.setEnabled(false);
    			surveyCalcText.setEnabled(false);
    			surveyNameTextASCIIDirecional.setEnabled(false);
    			selectButton.setEnabled(false);
    			referenceCombo.setEnabled(false);
    			remarksText.setEnabled(false);
    			overwriteASCIIDirecional.setEnabled(false);
    		}
    	});
    }
    
    private void createASCIILithology(Composite shell, Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableASCIILithology = new Button(composite, SWT.CHECK);
    	enableASCIILithology.setText("Enable Lithology Update");
    	enableASCIILithology.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableASCIILithology.setLayoutData(gridData);
    	
    	Button overwriteASCIILithology = new Button(composite, SWT.CHECK);
    	overwriteASCIILithology.setEnabled(false);
    	overwriteASCIILithology.setText("Overwrite");
    	
    	radioASCIILithology = new Button(composite, SWT.RADIO);
    	radioASCIILithology.setText("Use as Total Depth");
    	radioASCIILithology.setEnabled(false);
    	    	
    	Label inputFileLabel = new Label(composite, SWT.NONE);
        inputFileLabel.setText("Input File:");
        inputFileTextASCIILithology = new Text(composite, SWT.BORDER);
        inputFileTextASCIILithology.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        inputFileTextASCIILithology.setEnabled(false);
        GridData gridData0 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData0.horizontalSpan = 3;
        inputFileTextASCIILithology.setLayoutData(gridData0);

        Button browseButton = new Button(composite, SWT.PUSH);
        browseButton.setText("Browse");
        browseButton.setEnabled(false);
        
        browseButton.addListener(SWT.Selection, event -> {
            FileDialog fileDialog = new FileDialog((Shell) shell, SWT.OPEN);
            fileDialog.setText("Select File");
            fileDialog.setFilterPath("C:/");
            fileDialog.setFilterExtensions(new String[] { "*.txt", "*.*" });

            String selectedFile = fileDialog.open();
            if (selectedFile != null) {
            	inputFileTextASCIILithology.setText(selectedFile);
            }
        });
        
        Label curveTypeLabel = new Label(composite, SWT.NONE);
        curveTypeLabel.setText("Curve Type:");
        curveTypeComboASCIILithology = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        curveTypeComboASCIILithology.setItems(new String[] { "" });
        curveTypeComboASCIILithology.select(0);
        curveTypeComboASCIILithology.setEnabled(false);
        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 4;
        curveTypeComboASCIILithology.setLayoutData(gridData1);
                
        Label remarksLabel = new Label(composite, SWT.NONE);
        remarksLabel.setText("Remarks:");
        Text remarksText = new Text(composite, SWT.BORDER);
        remarksText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        remarksText.setEnabled(false);
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 4;
        remarksText.setLayoutData(gridData2);
    	
    	enableASCIILithology.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			overwriteASCIILithology.setEnabled(true);
    			radioASCIILithology.setEnabled(true);
    			inputFileTextASCIILithology.setEnabled(true);
    			browseButton.setEnabled(true);
    			curveTypeComboASCIILithology.setEnabled(true);
    			remarksText.setEnabled(true);
    		}
    		else {
    			overwriteASCIILithology.setEnabled(false);
    			radioASCIILithology.setEnabled(false);
    			inputFileTextASCIILithology.setEnabled(false);
    			browseButton.setEnabled(false);
    			curveTypeComboASCIILithology.setEnabled(false);
    			remarksText.setEnabled(false);
    		}
    	});
    }
    
    private void createASCIILogCurves(Composite shell, Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableASCIILogCurves = new Button(composite, SWT.CHECK);
    	enableASCIILogCurves.setText("Enable Log Curves Update");
    	enableASCIILogCurves.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableASCIILogCurves.setLayoutData(gridData);
    	
    	Button overwriteASCIILogCurves = new Button(composite, SWT.CHECK);
    	overwriteASCIILogCurves.setEnabled(false);
    	overwriteASCIILogCurves.setText("Overwrite");
    	
    	radioASCIILogCurves = new Button(composite, SWT.RADIO);
    	radioASCIILogCurves.setText("Use as Total Depth");
    	radioASCIILogCurves.setEnabled(false);
    	
    	
    	Label inputFileLabel = new Label(composite, SWT.NONE);
        inputFileLabel.setText("Input File:");
        Text inputFileTextASCIILogCurves = new Text(composite, SWT.BORDER);
        inputFileTextASCIILogCurves.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        inputFileTextASCIILogCurves.setEnabled(false);
        GridData gridData0 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData0.horizontalSpan = 3;
        inputFileTextASCIILogCurves.setLayoutData(gridData0);

        Button browseButton = new Button(composite, SWT.PUSH);
        browseButton.setText("Browse");
        browseButton.setEnabled(false);
        
        browseButton.addListener(SWT.Selection, event -> {
            FileDialog fileDialog = new FileDialog((Shell) shell, SWT.OPEN);
            fileDialog.setText("Select File");
            fileDialog.setFilterPath("C:/");
            fileDialog.setFilterExtensions(new String[] { "*.txt", "*.*" });

            String selectedFile = fileDialog.open();
            if (selectedFile != null) {
            	inputFileTextASCIILogCurves.setText(selectedFile);
            }
        });
        
        Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        String[] titles = { "Get Curve", "Original Name", "Store Name", "Service", "Run", "Pass", "Version", "Value Unit", "Null Value", "Multiplier" };
        
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.NULL);
            column.setText(titles[loopIndex]);
        }
        
        //Mock Items
        for (int loopIndex = 0; loopIndex < 5; loopIndex++) {
            TableItem item = new TableItem(table, SWT.NULL);
            item.setText(0, "Item " + loopIndex);
            item.setText(1, "Depth");
            item.setText(2, "Depth");
            item.setText(3, "Ascii");
            item.setText(4, "3");
            item.setText(5, "0");
            item.setText(6, "1");
            item.setText(7, "ft");
            item.setText(8, "");
            item.setText(9, "1");
        }

        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }

        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 5;
        table.setLayoutData(gridData1);
        table.setEnabled(false);
        
    	enableASCIILogCurves.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			overwriteASCIILogCurves.setEnabled(true);
    			inputFileTextASCIILogCurves.setEnabled(true);
    			browseButton.setEnabled(true);
    			radioASCIILogCurves.setEnabled(true);
    			table.setEnabled(true);
    		}
    		else {
    			overwriteASCIILogCurves.setEnabled(false);
    			inputFileTextASCIILogCurves.setEnabled(false);
    			browseButton.setEnabled(false);
    			radioASCIILogCurves.setEnabled(false);
    			table.setEnabled(false);
    		}
    	});
    }
    
    private void createASCIIDrillingParameters(Composite shell, Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableASCIIDrillingParameters = new Button(composite, SWT.CHECK);
    	enableASCIIDrillingParameters.setText("Enable Log Curves Update");
    	enableASCIIDrillingParameters.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableASCIIDrillingParameters.setLayoutData(gridData);
    	
    	Button overwriteASCIIDrillingParameters = new Button(composite, SWT.CHECK);
    	overwriteASCIIDrillingParameters.setEnabled(false);
    	overwriteASCIIDrillingParameters.setText("Overwrite");
    	
    	radioASCIIDrillingParameters = new Button(composite, SWT.RADIO);
    	radioASCIIDrillingParameters.setText("Use as Total Depth");
    	radioASCIIDrillingParameters.setEnabled(false);
    	    	
    	Label inputFileLabel = new Label(composite, SWT.NONE);
        inputFileLabel.setText("Input File:");
        inputFileTextASCIIDrilling = new Text(composite, SWT.BORDER);
        inputFileTextASCIIDrilling.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        inputFileTextASCIIDrilling.setEnabled(false);
        GridData gridData0 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData0.horizontalSpan = 3;
        inputFileTextASCIIDrilling.setLayoutData(gridData0);

        Button browseButton = new Button(composite, SWT.PUSH);
        browseButton.setText("Browse");
        browseButton.setEnabled(false);
        
        browseButton.addListener(SWT.Selection, event -> {
            FileDialog fileDialog = new FileDialog((Shell) shell, SWT.OPEN);
            fileDialog.setText("Select File");
            fileDialog.setFilterPath("C:/");
            fileDialog.setFilterExtensions(new String[] { "*.txt", "*.*" });

            String selectedFile = fileDialog.open();
            if (selectedFile != null) {
            	inputFileTextASCIIDrilling.setText(selectedFile);
            }
        });
        
        Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        String[] titles = { "Get Curve", "Original Name", "Store Name", "Service", "Run", "Pass", "Version", "Value Unit", "Null Value", "Multiplier" };
        
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.NULL);
            column.setText(titles[loopIndex]);
        }
        
        //Mock Items
        for (int loopIndex = 0; loopIndex < 5; loopIndex++) {
            TableItem item = new TableItem(table, SWT.NULL);
            item.setText(0, "Item " + loopIndex);
            item.setText(1, "Depth");
            item.setText(2, "Depth");
            item.setText(3, "Ascii");
            item.setText(4, "3");
            item.setText(5, "0");
            item.setText(6, "1");
            item.setText(7, "ft");
            item.setText(8, "");
            item.setText(9, "1");
        }

        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }

        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 5;
        table.setLayoutData(gridData1);
        table.setEnabled(false);
        
    	enableASCIIDrillingParameters.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			overwriteASCIIDrillingParameters.setEnabled(true);
    			inputFileTextASCIIDrilling.setEnabled(true);
    			browseButton.setEnabled(true);
    			radioASCIIDrillingParameters.setEnabled(true);
    			table.setEnabled(true);
    		}
    		else {
    			overwriteASCIIDrillingParameters.setEnabled(false);
    			inputFileTextASCIIDrilling.setEnabled(false);
    			browseButton.setEnabled(false);
    			radioASCIIDrillingParameters.setEnabled(false);
    			table.setEnabled(false);
    		}
    	});
    }
    
    private void createWitsmlDirecionalFields(Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableWitsmlDirecional = new Button(composite, SWT.CHECK);
    	enableWitsmlDirecional.setText("Enable Directional Update");
    	enableWitsmlDirecional.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableWitsmlDirecional.setLayoutData(gridData);
    	
    	Button overwriteWitsmlDirecional = new Button(composite, SWT.CHECK);
    	overwriteWitsmlDirecional.setEnabled(false);
    	overwriteWitsmlDirecional.setText("Overwrite");
    	
    	radioWitsmlDirectional = new Button(composite, SWT.RADIO);
    	radioWitsmlDirectional.setText("Use as Total Depth");
    	radioWitsmlDirectional.setEnabled(false);
        
    	Label surveyCalcLabel = new Label(composite, SWT.NONE);
        surveyCalcLabel.setText("Survey Calc Mthd:");
        Combo surveyCalcText = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        surveyCalcText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        surveyCalcText.setItems(new String[] { "Angle Averaging", "Balanced Tangential", "Minimum Curvature", "Radius of Curvature", "Tangential", "Unknown" });
        surveyCalcText.select(2);
        surveyCalcText.setEnabled(false);
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 2;
        surveyCalcText.setLayoutData(gridData2);
        Label surveyCalcLabel2 = new Label(composite, SWT.NONE);
        surveyCalcLabel2.setText("North Reference:");;
        Combo referenceCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        referenceCombo.setItems(new String[] { "Unknown", "Grid", "True" });
        referenceCombo.select(1);
        referenceCombo.setEnabled(false);
        
        Label surveyNameLabel = new Label(composite, SWT.NONE);
        surveyNameLabel.setText("Survey Name:");
        surveyNameTextWitsmlDirecional = new Text(composite, SWT.BORDER);
        surveyNameTextWitsmlDirecional.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        surveyNameTextWitsmlDirecional.setEnabled(false);
        GridData gridData4 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData4.horizontalSpan = 3;
        surveyNameTextWitsmlDirecional.setLayoutData(gridData4);
        
        Button selectButton = new Button(composite, SWT.PUSH);
        selectButton.setText("Select...");
        selectButton.setEnabled(false);

        Label remarksLabel = new Label(composite, SWT.NONE);
        remarksLabel.setText("Remarks:");
        Text remarksText = new Text(composite, SWT.BORDER);
        remarksText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        remarksText.setEnabled(false);
        GridData gridData3 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData3.horizontalSpan = 4;
        remarksText.setLayoutData(gridData3);
        
        enableWitsmlDirecional.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			radioWitsmlDirectional.setEnabled(true);
    			surveyCalcText.setEnabled(true);
    			surveyNameTextWitsmlDirecional.setEnabled(true);
    			selectButton.setEnabled(true);
    			referenceCombo.setEnabled(true);
    			remarksText.setEnabled(true);
    			overwriteWitsmlDirecional.setEnabled(true);
    		}
    		else {
    			radioWitsmlDirectional.setEnabled(false);
    			surveyCalcText.setEnabled(false);
    			surveyNameTextWitsmlDirecional.setEnabled(false);
    			selectButton.setEnabled(false);
    			referenceCombo.setEnabled(false);
    			remarksText.setEnabled(false);
    			overwriteWitsmlDirecional.setEnabled(false);
    		}
    	});
    }
    
    private void createWitsmlLogCurves(Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableWitsmlLogCurves = new Button(composite, SWT.CHECK);
    	enableWitsmlLogCurves.setText("Enable Log Curves Update");
    	enableWitsmlLogCurves.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableWitsmlLogCurves.setLayoutData(gridData);
    	
    	Button overwriteWitsmlLogCurves = new Button(composite, SWT.CHECK);
    	overwriteWitsmlLogCurves.setEnabled(false);
    	overwriteWitsmlLogCurves.setText("Overwrite");
    	
    	radioWitsmlLogCurves = new Button(composite, SWT.RADIO);
    	radioWitsmlLogCurves.setText("Use as Total Depth");
    	radioWitsmlLogCurves.setEnabled(false);
    	    	
    	Label logServiceLabel = new Label(composite, SWT.NONE);
    	logServiceLabel.setText("Log Service:");
        logServiceComboWitsmlLogCurves = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        logServiceComboWitsmlLogCurves.setItems(new String[] { "" });
        logServiceComboWitsmlLogCurves.select(0);
        logServiceComboWitsmlLogCurves.setEnabled(false);
        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 4;
        logServiceComboWitsmlLogCurves.setLayoutData(gridData1);
                
        Label mnemonicDepthLabel = new Label(composite, SWT.NONE);
        mnemonicDepthLabel.setText("Mnemonic Depth:");
        mnemonicDepthComboWitsmlLogCurves = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        mnemonicDepthComboWitsmlLogCurves.setItems(new String[] { "" });
        mnemonicDepthComboWitsmlLogCurves.select(0);
        mnemonicDepthComboWitsmlLogCurves.setEnabled(false);
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 4;
        mnemonicDepthComboWitsmlLogCurves.setLayoutData(gridData2);
                
        Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        String[] titles = { "Get Curve", "Original Name", "Store Name", "Service", "Run", "Pass", "Version", "Value Unit", "Null Value", "Multiplier" };
        
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.NULL);
            column.setText(titles[loopIndex]);
        }
        
        //Mock Items
        for (int loopIndex = 0; loopIndex < 5; loopIndex++) {
            TableItem item = new TableItem(table, SWT.NULL);
            item.setText(0, "Item " + loopIndex);
            item.setText(1, "Depth");
            item.setText(2, "Depth");
            item.setText(3, "Witsml");
            item.setText(4, "3");
            item.setText(5, "0");
            item.setText(6, "1");
            item.setText(7, "ft");
            item.setText(8, "");
            item.setText(9, "1");
        }

        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }

        GridData gridData3 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData3.horizontalSpan = 5;
        table.setLayoutData(gridData3);
        table.setEnabled(false);
        
    	enableWitsmlLogCurves.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			overwriteWitsmlLogCurves.setEnabled(true);
    			radioWitsmlLogCurves.setEnabled(true);
    			table.setEnabled(true);
    			logServiceComboWitsmlLogCurves.setEnabled(true);
    			mnemonicDepthComboWitsmlLogCurves.setEnabled(true);
    		}
    		else {
    			overwriteWitsmlLogCurves.setEnabled(false);
    			radioWitsmlLogCurves.setEnabled(false);
    			table.setEnabled(false);
    			logServiceComboWitsmlLogCurves.setEnabled(false);
    			mnemonicDepthComboWitsmlLogCurves.setEnabled(false);
    		}
    	});
    }
    
    private void createExataDirecionalFields(Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableExataDirecional = new Button(composite, SWT.CHECK);
    	enableExataDirecional.setText("Enable Directional Update");
    	enableExataDirecional.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableExataDirecional.setLayoutData(gridData);
    	
    	Button overwriteExataDirecional = new Button(composite, SWT.CHECK);
    	overwriteExataDirecional.setEnabled(false);
    	overwriteExataDirecional.setText("Overwrite");
    	
    	radioExataDirectional = new Button(composite, SWT.RADIO);
    	radioExataDirectional.setText("Use as Total Depth");
    	radioExataDirectional.setEnabled(false);
        
    	Label surveyCalcLabel = new Label(composite, SWT.NONE);
        surveyCalcLabel.setText("Survey Calc Mthd:");
        Combo surveyCalcText = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        surveyCalcText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        surveyCalcText.setItems(new String[] { "Angle Averaging", "Balanced Tangential", "Minimum Curvature", "Radius of Curvature", "Tangential", "Unknown" });
        surveyCalcText.select(2);
        surveyCalcText.setEnabled(false);
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 2;
        surveyCalcText.setLayoutData(gridData2);
        Label surveyCalcLabel2 = new Label(composite, SWT.NONE);
        surveyCalcLabel2.setText("North Reference:");;
        Combo referenceCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        referenceCombo.setItems(new String[] { "Unknown", "Grid", "True" });
        referenceCombo.select(1);
        referenceCombo.setEnabled(false);
        
        Label surveyNameLabel = new Label(composite, SWT.NONE);
        surveyNameLabel.setText("Survey Name:");
        surveyNameTextExataDirecional = new Text(composite, SWT.BORDER);
        surveyNameTextExataDirecional.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        surveyNameTextExataDirecional.setEnabled(false);
        GridData gridData4 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData4.horizontalSpan = 3;
        surveyNameTextExataDirecional.setLayoutData(gridData4);
        
        Button selectButton = new Button(composite, SWT.PUSH);
        selectButton.setText("Select...");
        selectButton.setEnabled(false);

        Label remarksLabel = new Label(composite, SWT.NONE);
        remarksLabel.setText("Remarks:");
        Text remarksText = new Text(composite, SWT.BORDER);
        remarksText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        remarksText.setEnabled(false);
        GridData gridData3 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData3.horizontalSpan = 4;
        remarksText.setLayoutData(gridData3);
        
        enableExataDirecional.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			radioExataDirectional.setEnabled(true);
    			surveyCalcText.setEnabled(true);
    			surveyNameTextExataDirecional.setEnabled(true);
    			selectButton.setEnabled(true);
    			referenceCombo.setEnabled(true);
    			remarksText.setEnabled(true);
    			overwriteExataDirecional.setEnabled(true);
    		}
    		else {
    			radioExataDirectional.setEnabled(false);
    			surveyCalcText.setEnabled(false);
    			surveyNameTextExataDirecional.setEnabled(false);
    			selectButton.setEnabled(false);
    			referenceCombo.setEnabled(false);
    			remarksText.setEnabled(false);
    			overwriteExataDirecional.setEnabled(false);
    		}
    	});
    }
    
    private void createExataLithology(Composite shell, Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableExataLithology = new Button(composite, SWT.CHECK);
    	enableExataLithology.setText("Enable Lithology Update");
    	enableExataLithology.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableExataLithology.setLayoutData(gridData);
    	
    	Button overwriteExataLithology = new Button(composite, SWT.CHECK);
    	overwriteExataLithology.setEnabled(false);
    	overwriteExataLithology.setText("Overwrite");
    	
    	radioExataLithology = new Button(composite, SWT.RADIO);
    	radioExataLithology.setText("Use as Total Depth");
    	radioExataLithology.setEnabled(false);
    	    	
    	Label curveTypeLabel = new Label(composite, SWT.NONE);
        curveTypeLabel.setText("Curve Type:");
        curveTypeComboExataLithology = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        curveTypeComboExataLithology.setItems(new String[] { "" });
        curveTypeComboExataLithology.select(0);
        curveTypeComboExataLithology.setEnabled(false);
        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 4;
        curveTypeComboExataLithology.setLayoutData(gridData1);
                
        Label remarksLabel = new Label(composite, SWT.NONE);
        remarksLabel.setText("Remarks:");
        Text remarksText = new Text(composite, SWT.BORDER);
        remarksText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        remarksText.setEnabled(false);
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 4;
        remarksText.setLayoutData(gridData2);
    	
    	enableExataLithology.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			overwriteExataLithology.setEnabled(true);
    			radioExataLithology.setEnabled(true);
    			curveTypeComboExataLithology.setEnabled(true);
    			remarksText.setEnabled(true);
    		}
    		else {
    			overwriteExataLithology.setEnabled(false);
    			radioExataLithology.setEnabled(false);
    			curveTypeComboExataLithology.setEnabled(false);
    			remarksText.setEnabled(false);
    		}
    	});
    }
    
    private void createExataLogCurves(Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableExataLogCurves = new Button(composite, SWT.CHECK);
    	enableExataLogCurves.setText("Enable Log Curves Update");
    	enableExataLogCurves.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableExataLogCurves.setLayoutData(gridData);
    	
    	Button overwriteExataLogCurves = new Button(composite, SWT.CHECK);
    	overwriteExataLogCurves.setEnabled(false);
    	overwriteExataLogCurves.setText("Overwrite");
    	
    	radioExataLogCurves = new Button(composite, SWT.RADIO);
    	radioExataLogCurves.setText("Use as Total Depth");
    	radioExataLogCurves.setEnabled(false);
    	    	
    	Label logServiceLabel = new Label(composite, SWT.NONE);
    	logServiceLabel.setText("Log Service:");
        logServiceComboExataLogCurves = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        logServiceComboExataLogCurves.setItems(new String[] { "" });
        logServiceComboExataLogCurves.select(0);
        logServiceComboExataLogCurves.setEnabled(false);
        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 4;
        logServiceComboExataLogCurves.setLayoutData(gridData1);
                
        Label mnemonicDepthLabel = new Label(composite, SWT.NONE);
        mnemonicDepthLabel.setText("Mnemonic Depth:");
        mnemonicDepthComboExataLogCurves = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        mnemonicDepthComboExataLogCurves.setItems(new String[] { "" });
        mnemonicDepthComboExataLogCurves.select(0);
        mnemonicDepthComboExataLogCurves.setEnabled(false);
        GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData2.horizontalSpan = 4;
        mnemonicDepthComboExataLogCurves.setLayoutData(gridData2);
                
        Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        String[] titles = { "Get Curve", "Original Name", "Store Name", "Service", "Run", "Pass", "Version", "Value Unit", "Null Value", "Multiplier" };
        
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.NULL);
            column.setText(titles[loopIndex]);
        }
        
        //Mock Items
        for (int loopIndex = 0; loopIndex < 5; loopIndex++) {
            TableItem item = new TableItem(table, SWT.NULL);
            item.setText(0, "Item " + loopIndex);
            item.setText(1, "Depth");
            item.setText(2, "Depth");
            item.setText(3, "Exata");
            item.setText(4, "3");
            item.setText(5, "0");
            item.setText(6, "1");
            item.setText(7, "ft");
            item.setText(8, "");
            item.setText(9, "1");
        }

        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }

        GridData gridData3 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData3.horizontalSpan = 5;
        table.setLayoutData(gridData3);
        table.setEnabled(false);
        
    	enableExataLogCurves.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			overwriteExataLogCurves.setEnabled(true);
    			radioExataLogCurves.setEnabled(true);
    			table.setEnabled(true);
    			logServiceComboExataLogCurves.setEnabled(true);
    			mnemonicDepthComboExataLogCurves.setEnabled(true);
    		}
    		else {
    			overwriteExataLogCurves.setEnabled(false);
    			radioExataLogCurves.setEnabled(false);
    			table.setEnabled(false);
    			logServiceComboExataLogCurves.setEnabled(false);
    			mnemonicDepthComboExataLogCurves.setEnabled(false);
    		}
    	});
    }
    
    private void createExataDrillingParameters(Composite shell, Composite composite) {
    	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        
    	enableExataDrillingParameters = new Button(composite, SWT.CHECK);
    	enableExataDrillingParameters.setText("Enable Drilling Parameters Update");
    	enableExataDrillingParameters.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	enableExataDrillingParameters.setLayoutData(gridData);
    	
    	Button overwriteExataDrillingParameters = new Button(composite, SWT.CHECK);
    	overwriteExataDrillingParameters.setEnabled(false);
    	overwriteExataDrillingParameters.setText("Overwrite");
    	
    	radioExataDrillingParameters = new Button(composite, SWT.RADIO);
    	radioExataDrillingParameters.setText("Use as Total Depth");
    	radioExataDrillingParameters.setEnabled(false);
    	    	
    	Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        String[] titles = { "Get Curve", "Original Name", "Store Name", "Service", "Run", "Pass", "Version", "Value Unit", "Null Value", "Multiplier" };
        
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.NULL);
            column.setText(titles[loopIndex]);
        }
        
        //Mock Items
        for (int loopIndex = 0; loopIndex < 5; loopIndex++) {
            TableItem item = new TableItem(table, SWT.NULL);
            item.setText(0, "Item " + loopIndex);
            item.setText(1, "Depth");
            item.setText(2, "Depth");
            item.setText(3, "Exata");
            item.setText(4, "3");
            item.setText(5, "0");
            item.setText(6, "1");
            item.setText(7, "ft");
            item.setText(8, "");
            item.setText(9, "1");
        }

        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }

        GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData1.horizontalSpan = 5;
        table.setLayoutData(gridData1);
        table.setEnabled(false);
        
    	enableExataDrillingParameters.addListener(SWT.Selection, event -> {
    		if (((Button) event.widget).getSelection()) {
    			overwriteExataDrillingParameters.setEnabled(true);
    			radioExataDrillingParameters.setEnabled(true);
    			table.setEnabled(true);
    		}
    		else {
    			overwriteExataDrillingParameters.setEnabled(false);
    			radioExataDrillingParameters.setEnabled(false);
    			table.setEnabled(false);
    		}
    	});
    }
    
    private boolean validateRequiredFields() {
    	if (connectionTypeCombo.getText().trim().isEmpty()) {
            showMessage("Error", "Connection Type is required!");
            return false;
        }
    	
    	if (pipelineNameText.getText().trim().isEmpty()) {
            showMessage("Error", "Pipeline Name is required!");
            return false;
        }
    	
    	if (connectionTypeCombo.getText().equals("ASCII")) {
    		if (enableASCIIDirecional.getSelection()) {
	    		if (inputFileTextASCIIDirecional.getText().trim().isEmpty()) {
	    			showMessage("Error", "Input File is required!");
	                return false;
	    		}
	    		
	    		if (surveyNameTextASCIIDirecional.getText().trim().isEmpty()) {
	    			showMessage("Error", "Survey Name is required!");
	                return false;
	    		}
    		}
    		
    		if (enableASCIILithology.getSelection()) {
	    		if (inputFileTextASCIILithology.getText().trim().isEmpty()) {
	    			showMessage("Error", "Input File is required!");
	                return false;
	    		}
	    		
	    		if (curveTypeComboASCIILithology.getText().trim().isEmpty()) {
	    			showMessage("Error", "Curve Type is required!");
	                return false;
	    		}
    		}
    		
    		if (enableASCIILogCurves.getSelection()) {
	    		if (inputFileTextASCIILogCurves.getText().trim().isEmpty()) {
	    			showMessage("Error", "Input File is required!");
	                return false;
	    		}
    		}
    		
    		if (enableASCIIDrillingParameters.getSelection()) {
	    		if (inputFileTextASCIIDrilling.getText().trim().isEmpty()) {
	    			showMessage("Error", "Input File is required!");
	                return false;
	    		}
    		}
    	}
    	else if (connectionTypeCombo.getText().equals("WITSML")) {
    		if (connectionCombo.getText().trim().isEmpty()) {
    			showMessage("Error", "Connection is required!");
                return false;
    		}
    		
    		if (uwiSourceCombo.getText().trim().isEmpty()) {
    			showMessage("Error", "UWI Source is required!");
                return false;
    		}
    		
    		if (enableWitsmlDirecional.getSelection()) {
	    		if (surveyNameTextWitsmlDirecional.getText().trim().isEmpty()) {
	    			showMessage("Error", "Survey Name is required!");
	                return false;
	    		}
    		}
    		
    		if (enableWitsmlLogCurves.getSelection()) {
	    		if (logServiceComboWitsmlLogCurves.getText().trim().isEmpty()) {
	    			showMessage("Error", "Log Service is required!");
	                return false;
	    		}
	    		
	    		if (mnemonicDepthComboWitsmlLogCurves.getText().trim().isEmpty()) {
	    			showMessage("Error", "Mnemonic Depth is required!");
	                return false;
	    		}
    		}
    	}
    	else if (connectionTypeCombo.getText().equals("EXATA")) {
    		if (uwiSourceCombo.getText().trim().isEmpty()) {
    			showMessage("Error", "UWI Source is required!");
                return false;
    		}
    		
    		if (enableExataDirecional.getSelection()) {
	    		if (surveyNameTextExataDirecional.getText().trim().isEmpty()) {
	    			showMessage("Error", "Survey Name is required!");
	                return false;
	    		}
    		}
    		
    		if (enableExataLithology.getSelection()) {
	    		if (curveTypeComboExataLithology.getText().trim().isEmpty()) {
	    			showMessage("Error", "Curve Type is required!");
	                return false;
	    		}
    		}
    		
    		if (enableExataLogCurves.getSelection()) {
	    		if (logServiceComboExataLogCurves.getText().trim().isEmpty()) {
	    			showMessage("Error", "Log Service is required!");
	                return false;
	    		}
	    		
	    		if (mnemonicDepthComboExataLogCurves.getText().trim().isEmpty()) {
	    			showMessage("Error", "Mnemonic Depth is required!");
	                return false;
	    		}
    		}
    	}
        
        if (uwiText.getText().trim().isEmpty()) {
            showMessage("Error", "UWI is required!");
            return false;
        }

        return true;
    }
    
    private void showMessage(String title, String message) {
        MessageBox dialog = new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK);
        dialog.setText(title);
        dialog.setMessage(message);
        dialog.open();
    }
}
