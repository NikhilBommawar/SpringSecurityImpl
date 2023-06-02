package com.unoveo.Calc;


import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;



//@WebServlet("/calc/*")
@Controller

public class Calc  {


    @RequestMapping("/calc")
//    @CrossOrigin(origins = "http://localhost:4200")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            double result;
//        response.setHeader("Access-Control-Allow-Methods", "POST");
            System.out.println("--- api called");
        System.out.println(request.getReader().lines().collect(Collectors.joining()));
            response.setContentType("application/json");

 // calling header methods for preflight
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");

//        setAccessControlHeaders(response);
//        response.setStatus(HttpServletResponse.SC_OK);

 // (Method 1) ----> to read json from request and convert to String using Collectors
            String exp2 = request.getReader().lines().collect(Collectors.joining());
            System.out.println("Got the expression from Frontend => " + exp2);

        Gson gson = new Gson();
//        String input = request.getParameter("inputModel"); // !!!!!!!!!!!!
        DoubleEvaluator eval = new DoubleEvaluator();

        // USING TYPETOKEN TO PARSE ARRAY OF INPUTMODEL
        ArrayList<InputModel> outputList = new Gson().fromJson(exp2, new TypeToken<ArrayList<InputModel>>() {}.getType());


        //  COUNTING THE LENGTH OF ARRAY
        int count = (int) outputList.stream().count();
        //  COUNTING THE NUMBER OF OPERATORS
        int noOfOperator = count/2;

   // ----------- SIR's LOGIC FOR MAIN CALCULATION  -------------------

        float firstOperand = 0;
                    String operation = "";


     for(int index =0; index< count; index++){
            if(outputList.get(index).getType().equals("NUMBER")){
                if(firstOperand == 0){
                    firstOperand = Float.parseFloat(outputList.get(index).getValue());
                    if(index>0 && outputList.get(index-1).getValue().equals("subtract")){
                         firstOperand = -firstOperand;

                     }
                }
                else{
                     firstOperand = evaluate(firstOperand, Float.parseFloat(outputList.get(index).getValue()),operation);
                  }
            }
          if((outputList.get(index).getType().equals("OPERATOR")) && (firstOperand != 0)){
               operation = outputList.get(index).getValue();
            }

        }

        System.out.println("firstOperand "+ firstOperand);
        response.getWriter().print(firstOperand);

     }

//     // This method is used for Double Evaluator  ###############
//    private String getOperator(String operation) {
//        String operator = "";
//        switch (operation) {
//
//            case "add":
//                operator = "+";  // not used secondOperand variable
//                break;
//
//            case "subtract":
//                operator = "-";
//                break;
//
//            case "multiply":
//                operator = "*";
//                break;
//
//            case "division":
//                operator = "/";
//                break;
//
//
//        }
//        return operator;
//    }


    // SWITCH CASE TO DECIDE THE OPERATION TO BE PERFORMED
     public float evaluate(float firstOperand,float secondOperand,String operation){

         switch (operation){

                case "add":
                    firstOperand = doAddition(firstOperand,secondOperand);  // not used secondOperand variable
                    break;

                case "subtract":
                    firstOperand = doSubtraction(firstOperand,secondOperand);  // not used secondOperand variable
                    break;

                case "multiply":
                    firstOperand = doMultiplication(firstOperand,secondOperand);
                    break;

                case "division":
                    firstOperand =  doDivision(firstOperand,secondOperand);
                    break;

                default :
                    break;

            }
            return firstOperand;
     }


     // METHODS TO PERFORM OPERATIONS
    private static float doAddition(float firstNo,float secondNo){
        return firstNo + secondNo;
    }

    private static float doSubtraction(float firstNo,float secondNo){
        return firstNo - secondNo;
    }

    private static float doMultiplication(float firstNo,float secondNo){
        return firstNo * secondNo;
    }
    private static float doDivision(float firstNo,float secondNo){
        return firstNo / secondNo;
    }



    //for Preflight Request
//    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);

    }

    //for Preflight Request
    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
    }
}





