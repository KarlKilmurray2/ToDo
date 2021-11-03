import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class ToDo{

  private JFrame jf;
  private JList<String> tasklist;
  private JButton add;
  private JButton delete;
  private JTextArea newTask;
  private ArrayList<String> tasks = new ArrayList<String>();

  public ToDo() throws IOException{

    jf = new JFrame();
    delete = new JButton("delete");
    add = new JButton("add");
    newTask = new JTextArea();
    tasks = getFile();
    tasklist = new JList<String>(tasks.toArray(new String[tasks.size()]));

    newTask.setBounds(100,10,200,20);
    add.setBounds(100,400,100,40);
    delete.setBounds(200,400,100,40);
    tasklist.setBounds(100,50,200,300);

    jf.add(newTask);
    jf.add(tasklist);
    jf.add(delete);
    jf.add(add);
    jf.setSize(400,500);
    jf.setLayout(null);
    jf.setVisible(true);

    delete.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        jf.getContentPane().remove(tasklist);
        tasks.remove(tasklist.getSelectedIndex());
        tasklist = new JList<String>(tasks.toArray(new String[tasks.size()]));
        tasklist.setBounds(100,50,200,300);
        jf.add(tasklist);
        jf.repaint();
        try{
          saveFile(tasks);
          }catch(Exception exc){}
      }
    });

    add.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String name = newTask.getText();
        tasks.add(name);
        jf.getContentPane().remove(tasklist);
        tasklist = new JList<String>(tasks.toArray(new String[tasks.size()]));
        tasklist.setBounds(100,50,200,300);
        jf.add(tasklist);
        jf.repaint();
        newTask.setText("");
        try{
        saveFile(tasks);
        }catch(Exception exc){}
      }
    });
  }

  public static void main(String args[]) throws Exception{
    new ToDo();
  }


  static ArrayList<String> getFile() throws IOException{
    ArrayList<String> tasks = new ArrayList<String>();
    String read;
    try{
      File saveData = new File("tasks.txt");
      if(saveData.exists()){
        BufferedReader br = new BufferedReader(new FileReader(saveData));
        while((read = br.readLine()) != null){
          tasks.add(read);
      }
      br.close();
      }
      else{
        saveData.createNewFile();
      }
    }catch(Exception e){
      System.out.println(e);
    }
    return tasks;
  }

  static void saveFile(ArrayList<String> tasks) throws IOException{
    try{
      File saveData = new File("tasks.txt");
      FileWriter saveFile = new FileWriter(saveData);
      if (!saveData.createNewFile()) {
        System.out.println("File created: " + saveData.getName());
      }
      for(String task : tasks){
        System.out.println(task);
        saveFile.write(task+"\n");
      }saveFile.close();
    } catch(Exception e){
      System.out.println(e);
    }
  }
}
