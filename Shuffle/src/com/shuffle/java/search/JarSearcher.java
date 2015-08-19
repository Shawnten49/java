/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shuffle.java.search;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class JarSearcher extends JFrame {

    private File choicedDirLocation;
    private JButton btnSearcher;
    private ButtonGroup buttonGroup;
    private JLabel lblResult;
    private JLabel lblSearchFileName;
    private JLabel lblSearchPath;
    private JRadioButton rdoChoiceNarrow;
    private JRadioButton rdoChoiceStrict;
    private JScrollPane scrollPane;
    private JTextField txtSearchFileName;
    private JTextField txtSearchPath;
    private JTextArea txtaResult;

    public JarSearcher() {
        initComponents();

        choicedDirLocation = new File("C:/");

        int windowWidth = getWidth();
        int windowHeight = getBounds().height;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);

        this.buttonGroup.add(this.rdoChoiceStrict);
        this.buttonGroup.add(this.rdoChoiceNarrow);
        this.rdoChoiceStrict.setSelected(true);
    }

    private void initComponents() {
        this.buttonGroup = new ButtonGroup();
        this.lblSearchPath = new JLabel();
        this.txtSearchPath = new JTextField();
        this.lblSearchFileName = new JLabel();
        this.txtSearchFileName = new JTextField();
        this.btnSearcher = new JButton();
        this.scrollPane = new JScrollPane();
        this.txtaResult = new JTextArea();
        this.lblResult = new JLabel();
        this.rdoChoiceStrict = new JRadioButton();
        this.rdoChoiceNarrow = new JRadioButton();

        setDefaultCloseOperation(3);
        setTitle("Jar文件查找");
        setResizable(false);

        this.lblSearchPath.setText("查询路径");

        this.lblSearchFileName.setText("查询类名");

        txtSearchPath.setEditable(false);
        txtSearchPath.addMouseListener(new MouseAdapter() {
            @Override
        public void mousePressed(MouseEvent evt) {
                txtSearchPathMousePressed(evt);
            }
        });

        txtSearchFileName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                txtSearchFileNameKeyPressed(evt);
            }
        });
        this.btnSearcher.setText("查询");
        btnSearcher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnSearcherActionPerformed(evt);
            }
        });
        this.txtaResult.setColumns(20);
        this.txtaResult.setEditable(false);
        this.txtaResult.setRows(5);
        this.scrollPane.setViewportView(this.txtaResult);

        this.rdoChoiceStrict.setSelected(true);
        this.rdoChoiceStrict.setText("严格匹配");

        this.rdoChoiceNarrow.setText("宽松匹配");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.scrollPane, GroupLayout.Alignment.LEADING, -1, 694, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.lblSearchPath, -2, 58, -2).addComponent(this.lblSearchFileName)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.txtSearchPath, -1, 630, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(this.rdoChoiceStrict).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.rdoChoiceNarrow)).addComponent(this.txtSearchFileName, GroupLayout.Alignment.LEADING, -1, 523, 32767).addComponent(this.lblResult, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.btnSearcher, -1, 101, 32767))))).addContainerGap()));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lblSearchPath).addComponent(this.txtSearchPath, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lblSearchFileName).addComponent(this.txtSearchFileName, -2, -1, -2).addComponent(this.btnSearcher)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.rdoChoiceStrict).addComponent(this.rdoChoiceNarrow)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.lblResult, -2, 19, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.scrollPane, -1, 307, 32767).addContainerGap()));

        pack();
    }

    private void txtSearchPathMousePressed(MouseEvent evt) {
        JFileChooser fc = new JFileChooser();

        fc.setDialogTitle("待查询目录选择");

        fc.setFileSelectionMode(1);

        fc.setCurrentDirectory(this.choicedDirLocation);

        int returnVal = fc.showOpenDialog(this);

        File selectedFile = null;
        if (returnVal == 0) {
            selectedFile = fc.getSelectedFile();
            this.txtSearchPath.setText(selectedFile.getPath());
        }
        this.choicedDirLocation = selectedFile;

        this.txtSearchFileName.setRequestFocusEnabled(true);
    }

    private void btnSearcherActionPerformed(ActionEvent evt) {
        handleProcess();
    }

    private void txtSearchFileNameKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == 10) {
            handleProcess();
            this.btnSearcher.setRequestFocusEnabled(true);
        }
    }

    private void handleProcess() {
        this.lblResult.setText("");
        this.txtaResult.setText("");

        if ("".equals(this.txtSearchPath.getText())) {
            JOptionPane.showMessageDialog(this, "请选择需查询的目录", "错误", 0);
            return;
        }
        if ("".equals(this.txtSearchFileName.getText())) {
            JOptionPane.showMessageDialog(this, "请填写需查询的类名", "错误", 0);
            return;
        }

        JarSearcherUtil search = new JarSearcherUtil(this.txtSearchPath.getText(), this.txtSearchFileName.getText());
        if (this.rdoChoiceStrict.isSelected()) {
            search.setMatchStyle(true);
        } else {
            search.setMatchStyle(false);
        }

        search.searchDir();
        if (search.getRetList().size() == 0) {
            JOptionPane.showMessageDialog(this, "没有查找到相符项目", "结果", 1);
        } else {
            this.txtaResult.setText(search.fetchSearchedFiles_ErrorFiles());
            this.txtaResult.select(0, 0);
            this.lblResult.setText("找到 " + search.getNumSize() + " 个项目");
            this.txtaResult.setRequestFocusEnabled(true);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                JarSearcher searcher = new JarSearcher();
                Image image = Toolkit.getDefaultToolkit().getImage(searcher.getClass().getResource("icon.gif"));
                searcher.setIconImage(image);
                searcher.setVisible(true);
            }
        });
    }
}
