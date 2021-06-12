package PackageFirst;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame /*implements ActionListener*/{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int POSITION_X = 650;
    private static final int POSITION_Y = 270;

    Font font = new Font("Gotham Pro", Font.BOLD, 17);

    JPanel panelCenter = new JPanel();
    JPanel panelRight = new JPanel();
    JPanel panelLeft = new JPanel();
    JPanel panelApp = new JPanel();

    JPanel[] panelsCenter = new JPanel[14];
    JLabel[] labelsCenter = new JLabel[7];
    JTextField[] textFieldsCenter = new JTextField[7];

    JTextField textFieldResultForMe = new JTextField();
    JTextField textFieldResultForClient = new JTextField();
    JTextField textFieldResultProfit = new JTextField();
    JTextField textFieldNameProduct = new JTextField();

    JPanel[] panelLefPanel = new JPanel[4];
    JButton[] ButtonLeftPanel = new JButton[4];


    double length;
    double height;
    double quantity;
    double priceForClient;
    double design;
    double installation;
    double taxi;

    double resultForMe;
    double resultForClient;
    double resultProfit;

    MainWindow() {

        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(POSITION_X, POSITION_Y);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Панели
        panelApp.setBackground(new Color(22,24,27));
        panelApp.setPreferredSize(new Dimension(100,50));

        panelLeft.setLayout(new GridLayout(4,1));// нужно отступы от стены добавть тоже !
        panelLeft.setBackground(new Color(37,40,46)); //тут она тоже по сути не нужна
        panelLeft.setPreferredSize(new Dimension(250,100));

        panelRight.setLayout(new GridLayout(7,0));
//        panelRight.setBackground(Color.LIGHT_GRAY); удалил цвет что бы все было одного фона
        panelRight.setPreferredSize(new Dimension(250,100));

        panelCenter.setLayout(new GridLayout(7,2));
        panelCenter.setBackground(new Color(37,40,46));
        panelCenter.setPreferredSize(new Dimension(100,100));

        for (int i = 0; i < 14; i++) {
            panelsCenter[i] = new JPanel();
        }

        for (int i = 0; i < 14; i++) {
            panelCenter.add(panelsCenter[i]);
        }

        for (int i = 0; i < 7; i++) {
            labelsCenter[i] = new JLabel();

            labelsCenter[i].setVerticalTextPosition(JLabel.CENTER);
            labelsCenter[i].setHorizontalTextPosition(JLabel.RIGHT);

            labelsCenter[i].setFont(font);
            labelsCenter[i].setPreferredSize(new Dimension(130,20));
        }

        for (int i = 0; i < 7; i++) {
            textFieldsCenter[i] = new JTextField();
            textFieldsCenter[i].setPreferredSize(new Dimension(50,35));
            textFieldsCenter[i].setFont(font);
        }

        panels();
        leftPanels();
        RightPanels();
        logic();

        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelApp, BorderLayout.NORTH);
        frame.add(panelRight, BorderLayout.EAST);
        frame.add(panelLeft, BorderLayout.WEST);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
    }

    public void RightPanels() {

        JLabel labelRightWan = new JLabel();
        labelRightWan.setFont(font);
        labelRightWan.setText("Мне:");


        JLabel labelRightToo = new JLabel();
        labelRightToo.setFont(font);
        labelRightToo.setText("Клиенту:");


        JLabel labelRightTree = new JLabel();
        labelRightTree.setFont(font);
        labelRightTree.setText("Прибыль:");


        textFieldResultForMe.setEditable(false);
        textFieldResultForClient.setEditable(false);
        textFieldResultProfit.setEditable(false);

        textFieldResultForMe.setFont(font);
        textFieldResultForClient.setFont(font);
        textFieldResultProfit.setFont(font);

        panelRight.add(labelRightWan);
        panelRight.add(textFieldResultForMe);

        panelRight.add(labelRightToo);
        panelRight.add(textFieldResultForClient);

        panelRight.add(labelRightTree);
        panelRight.add(textFieldResultProfit);

        textFieldNameProduct.setText("Наименование товара");
        panelRight.add(textFieldNameProduct);
    }

    public void leftPanels() {

        for (int i = 0; i < 4; i++) {
            ButtonLeftPanel[i] = new JButton();
            ButtonLeftPanel[i].setFont(font);
        }

        for (int i = 0; i < 4; i++) {
            panelLefPanel[i] = new JPanel();
            panelLefPanel[i].add(ButtonLeftPanel[i]);
            panelLeft.add(ButtonLeftPanel[i]);
        }

        ButtonLeftPanel[3].setText("Пленка");
        ButtonLeftPanel[2].setText("Ввод");
        ButtonLeftPanel[1].setText("Перфопленка");
        ButtonLeftPanel[0].setText("Баннер");

        for (int i = 0; i < 3; i++) {
            panelLeft.add(ButtonLeftPanel[i]);
        }
    }

    public void logic() {

        ButtonLeftPanel[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                length = Double.parseDouble(textFieldsCenter[0].getText());
                height = Double.parseDouble(textFieldsCenter[1].getText());
                quantity = Double.parseDouble(textFieldsCenter[2].getText());
                priceForClient = Double.parseDouble(textFieldsCenter[3].getText());
                design = Double.parseDouble(textFieldsCenter[4].getText());
                installation = Double.parseDouble(textFieldsCenter[5].getText());
                taxi = Double.parseDouble(textFieldsCenter[6].getText());

                resultForMe = (length * height) * quantity * 250;
                resultForClient = (length * height) * quantity * (priceForClient + installation) + taxi + design;
                resultProfit = resultForClient - resultForMe - taxi;

                textFieldResultForMe.setText(String.valueOf(resultForMe));
                textFieldResultForClient.setText(String.valueOf(resultForClient));
                textFieldResultProfit.setText(String.valueOf(resultProfit));
            }
        });
    }

    public void panels() {

        labelsCenter[6].setText("За такси");
        labelsCenter[5].setText("За установку");
        labelsCenter[4].setText("Дизайн");
        labelsCenter[3].setText("Цена клиенту");
        labelsCenter[2].setText("Количество");
        labelsCenter[1].setText("Высота");
        labelsCenter[0].setText("Длинна");

        panelsCenter[12].add(labelsCenter[6]);
        panelsCenter[10].add(labelsCenter[5]);
        panelsCenter[8].add(labelsCenter[4]);
        panelsCenter[6].add(labelsCenter[3]);
        panelsCenter[4].add(labelsCenter[2]);
        panelsCenter[2].add(labelsCenter[1]);
        panelsCenter[0].add(labelsCenter[0]);

        panelsCenter[13].add(textFieldsCenter[6]);
        panelsCenter[11].add(textFieldsCenter[5]);
        panelsCenter[9].add(textFieldsCenter[4]);
        panelsCenter[7].add(textFieldsCenter[3]);
        panelsCenter[5].add(textFieldsCenter[2]);
        panelsCenter[3].add(textFieldsCenter[1]);
        panelsCenter[1].add(textFieldsCenter[0]); //это самый верхний
    }
    //https://www.youtube.com/watch?v=3TdDa-aY-z8  через setBounds Можно задать размер
}