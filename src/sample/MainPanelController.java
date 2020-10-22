package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {
    public TextArea playersOffTextField;
    public TextArea villagesTextField;
    public CheckBox quarterOffCheckBox;
    public CheckBox halfOffCheckBox;
    public CheckBox threeFourOffCheckBox;
    public CheckBox fullOffCheckBox;
    public TextField numberOfAttacksLabel;
    public Label allPlayersOff;
    public Label allAttacks;

    private int numberOfAttacks = 15;
    private int attacksToOneVillage = 1;    //tutaj ataki na jedna wies od jednego gracza

    List<Player> players = new ArrayList<>();
    List<Village> villages = new ArrayList<>();
    List<PlayerObjectives> playerObjectives = new ArrayList<>();

    private void clearLists(){
        //players.clear();
       // villages.clear();
       // playerObjectives.clear();
    }

    private void readCSVPlayers() throws IOException {
       // clearLists();
        players.clear();
        BufferedReader br = new BufferedReader(new StringReader(playersOffTextField.getText()));

        String line = br.readLine();

        while(line != null){
            String[] attributes = line.split(",");
            Player player = createPlayer(attributes);

            players.add(player);
            System.out.println(player);

            line = br.readLine();
        }
    }

    private void readCSVVillages() throws IOException {
        villages.clear();
        BufferedReader br = new BufferedReader(new StringReader(villagesTextField.getText()));

        int tempNumberOfAttacks;
        String line =  br.readLine();

        while(line != null){
            String[] attributes = line.split(",");
            if(attributes.length>1) tempNumberOfAttacks=Integer.parseInt(attributes[1]);
            else tempNumberOfAttacks = numberOfAttacks;
            Village village = new Village(attributes[0],tempNumberOfAttacks);
            villages.add(village);
            System.out.println(village);

            line = br.readLine();
        }
    }


    private void shareVillages(){
        int i = 0;
        int tempVillage;
        for(Village village : villages){    //iterowanie po wszystkich celach
            i=0;
           while(village.getNumberOfAttacks()>0){       //jesli cel nadal ma ataki do rozpisania to wejdz do petli ktora wykona sie max 3 razy
               i++;
               if(i==10){
                   break;
               }
               // JESLI offow na jedna wies wiecej niz 3 to dodawac do tego samego obiektu - EQUALS?
               for(Player player : players){    //iterowanie po graczach z rozpiski
                   if(player.getCountedOffs()>=attacksToOneVillage){    //jesli gracz ma wiecej offow niz max. liczba offow na jedna wies
                        if(village.getNumberOfAttacks()>=attacksToOneVillage){
                            village.setNumberOfAttacks(village.getNumberOfAttacks()-attacksToOneVillage);
                            tempVillage = attacksToOneVillage;
                        }
                        else{
                            tempVillage = village.getNumberOfAttacks();
                            village.setNumberOfAttacks(0);
                        }
                        player.setCountedOffs(player.getCountedOffs()-tempVillage);
                        searchPlayerObjective(village.getCords(),tempVillage,player,village);
                       //SEARCH COS NIE DZIALA
                       // PlayerObjectives pvo = new PlayerObjectives(village.getCords(),tempVillage);
                        //player.objectives.add(pvo);
                   }
                   else if(player.getCountedOffs()>0){  //jesli gracz ma mniej offow niz max.liczba offow ale wiecej niz 0
                       PlayerObjectives pvo = new PlayerObjectives(village.getCords(),player.getCountedOffs());
                       village.setNumberOfAttacks(village.getNumberOfAttacks()-player.getCountedOffs());
                       player.setCountedOffs(0);
                       player.objectives.add(pvo);
                   }
                   else{    //jesli gracz juz nie ma offow
                   }
                   System.out.println("Pozostale offy dla celu: " + village.getCords() + village.getNumberOfAttacks());
                   if(village.getNumberOfAttacks()==0) break;       //jesli offy dla danego celu zostaly rozpisane to przerwij petle
               }
           }
        }
    }

    public void searchPlayerObjective(String name,int number, Player player, Village village){
        boolean foundCords = false;
        PlayerObjectives pvo = new PlayerObjectives(village.getCords(),number);
        if(player.objectives.size()==0) player.objectives.add(pvo);
        else{
            for(PlayerObjectives po : player.objectives){
                if(name.equals(po.getCords())){
                    System.out.println("IF");
                    po.setNumberOfAttacks(po.getNumberOfAttacks()+number);
                    foundCords = true;
                }
            }
            if(!foundCords){
                player.objectives.add(pvo);
            }
        }
    }

    private Player createPlayer(String[] csvData){
        String name = csvData[0];
        int fullOff = Integer.parseInt(csvData[1]);
        int threefourOff = Integer.parseInt(csvData[2]);
        int halfOff = Integer.parseInt(csvData[3]);
        int quarterOff = Integer.parseInt(csvData[4]);

        int tempOffCount = 0;

        if(fullOffCheckBox.isSelected()) tempOffCount+=fullOff;
        if(threeFourOffCheckBox.isSelected()) tempOffCount+=threefourOff;
        if(halfOffCheckBox.isSelected()) tempOffCount+=halfOff;
        if(quarterOffCheckBox.isSelected()) tempOffCount+=quarterOff;

        return new Player(name,fullOff,threefourOff,halfOff,quarterOff,tempOffCount);
    }

    public void generateListOnClick(ActionEvent actionEvent) throws IOException {
        if(numberOfAttacksLabel.getText().trim().isEmpty()==false){
            numberOfAttacks = Integer.parseInt(numberOfAttacksLabel.getText());
        }
        System.out.println("WLAZLEM");
        readCSVPlayers();
        System.out.println("WLAZLEM");
        readCSVVillages();
        sortPlayers();
        System.out.println("WLAZLEM");
        shareVillages();
        System.out.println("WLAZLEM");
        writeToFile();
    }

    public void writeToFile() throws IOException {

        FileWriter writer = new FileWriter("plan.txt");
        BufferedWriter bw = new BufferedWriter(writer);

        for(Player player : players){
            bw.write(player.getName() + "\n" + "[KORDY CELU] [LICZBA OFFOW]" + "\n");
            for(int i=0;i<player.objectives.size();i++){
                bw.write(player.objectives.get(i).toString());
            }
        }
        bw.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        villagesTextField.setPromptText("Podaj cele w formie:\r[KORDY]\r[KORDY]\r[KORDY]\r\rLub w formie:\r[KORDY],[LICZBA ATAKOW]\r[KORDY],[LICZBA ATAKOW]\r\r" +
                "PRZYKLADY:\r456|567\r456|563\r458|568,10");
    }

    public void countNumberOfAttacksOnClick(ActionEvent actionEvent) throws IOException {
        players.clear();
        readCSVPlayers();
        int offCount = 0;
        for(Player player : players){
            offCount+=player.getCountedOffs();
        }
        allPlayersOff.setText(String.valueOf(offCount));
    }

    public void countNumberOfAttacksGoalsOnClick(ActionEvent actionEvent) throws IOException {
        villages.clear();
        if(numberOfAttacksLabel.getText().trim().isEmpty()==false){
            numberOfAttacks = Integer.parseInt(numberOfAttacksLabel.getText());
        }
        readCSVVillages();
        int attacksCount = 0;
        for(Village village : villages){
            attacksCount+=village.getNumberOfAttacks();
        }
        allAttacks.setText(String.valueOf(attacksCount));
    }

    public void sortPlayers(){
        //ZROBIC SORTOWANKO
        Collections.sort(players,Collections.reverseOrder());
    }
}
