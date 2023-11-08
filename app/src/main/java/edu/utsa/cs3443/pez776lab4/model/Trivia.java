package edu.utsa.cs3443.pez776lab4.model;

import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Scanner;

import edu.utsa.cs3443.pez776lab4.MainActivity;

/*
The Trivia.java class will represent the trivia game in the “Trick” view of this app.
 The class Trivia will have an object method called loadTrivia(…) which takes in a file name and stores 1 piece of trivia from that file.
 Triva information consists of 1 question, 3 possible answers, and 1 correct response.
 We will limit the number of possible answers to any trivia question to 3 for the sake of this lab.
 The loadTrivia(…) method should read in 1 randomly chosen piece of trivia each time it is called, so that the user in our app sees a different question when they choose “Trick” from the main view.
 **/
public class Trivia {

    private String question;

    private String[] options = new String[3];

    private String response;

    private String correctAnswer;

    public Trivia(String question, String[] options, String response){
        this.question = question;
        this.options = options;
        this.response = response;
    }

    public Trivia(){
        System.out.println("Explicit default constructor");
    }
    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return this.question;
    }

    public void setOptions(String[] options){
        this.options = options;
    }

    public String[] getOptions(){
        return this.options;

    }
    public void setResponse(String response){
        this.response = response;
    }

    public String getResponse(){
        return this.response;
    }

    public String getOption(int index){
        return options[index];
    }

    public Trivia loadTrivia(MainActivity activity){
        AssetManager manager = activity.getAssets();
        Scanner scanner;

        try{
            InputStream stream = manager.open("trivia.csv");
            scanner = new Scanner(stream);
            int i = 0;
            while(scanner.hasNextLine()){
                i++;
            }
            SecureRandom sRandom = new SecureRandom();
            int lineNumber = sRandom.nextInt(i) + 1;

            int j = 1;
            String line = "";
            while(j < lineNumber){
                line = scanner.nextLine();
            }
            line  = scanner.nextLine();
            String[] lineSplit = line.trim().split(",");
            this.question = lineSplit[0];
            this.options[0] = lineSplit[1].trim();
            this.options[1] = lineSplit[2].trim();
            this.options[2] = lineSplit[3].trim();
            this.response = lineSplit[4];
            for(int k =5; k< lineSplit.length;k++){
                this.response+= this.response + ","+ lineSplit[k];
            }
            return (this);
        }catch(FileNotFoundException e){
            System.err.println("File not found");
        }catch (IOException e){
            System.err.println("IO exception, which is a super class of FileNotFoundException");
        }

        return (this);
    }

    private void identifyCorrectAnswer(){

    }

}
