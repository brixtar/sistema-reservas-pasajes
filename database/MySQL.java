/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class MySQL {

//Constructor de clase que se conecta a la base de datos SQLite 
    static String bd = "viajes"; 
    static String login = "viajes"; 
    static String password = "viajes2020"; 
    static String url = "jdbc:mysql://localhost/"+bd; 

    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;


//Constructor de clase que se conecta a la base de datos SQLite 
    public MySQL()
    {
      try{
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection(url,login,password);
         System.out.println("Conectado a la base de datos MySQL [ " + this.bd + "]");
      }catch(Exception e){
         JOptionPane.showMessageDialog(null, "No se pudo conectar a la Base de Datos. Revise que el programa XAMPP este funcionando correctamente","ERROR",0);
         System.out.println(e);
      }

    }
    
  /* METODO  PARA VER SI UN REGISTRO EXISTE LA BASE DE DATOS
 * INPUT:
 tabla = Nombre de la tabla
 colName = String con el nombre de columna
 value = String con el dato a buscar
 * OUTPUT:
 * Boolean
*/
 public boolean existe(String tabla, String colName, String value){
     int registros = 0;
     //Se arma la consulta
     String q = "SELECT count(1) as total FROM "+ tabla + " WHERE "+colName+" = "+value+""; 
     //Se ejecuta la consulta
     try{         
         System.out.println("**EXISTE**: "+q);
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
         System.out.println("Hay un total de "+registros+" registros");
      }catch(SQLException e){
         System.out.println(e);
      }
     if (registros >0){return true;}else{return false;}    
  }  
 
   /* METODO  PARA VER SI UN REGISTRO EXISTE LA BASE DE DATOS
 * INPUT:
 tabla = Nombre de la tabla
 colName = String con el nombre de columna
 value = String con el dato a buscar
 * OUTPUT:
 * Boolean
*/
 public boolean existe2variables(String tabla, String colName, String value, String colName2, String value2){
     int registros = 0;
     //Se arma la consulta
     String q = "SELECT count(1) as total FROM "+ tabla + " WHERE "+colName+" = "+value+" AND "+colName2+" = "+value2+""; 
     //Se ejecuta la consulta
     try{         
         System.out.println("**EXISTE**: "+q);
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
         System.out.println("Hay un total de "+registros+" registros");
      }catch(SQLException e){
         System.out.println(e);
      }
     if (registros >0){return true;}else{return false;}    
  }  
 
 
/* METODO PARA REALIZAR SELECCIONAR LOS REGISTROS  QUE CONTENGAN UNA CADENA (LIKE EN SQL), Y VOLCARLOS EN UNA JTABLE
 * INPUT: 
  colName= nombtre de columna
  tabla=nombre de tabla
  value=cadena a buscar
 * OUTPUT: String con los datos concatenados
*/
 public Object [][] SelectTabla(String colName[],String tabla, String sql){
     
      int registros = 0;
      //se arma la consulta 
      //obtenemos la cantidad de registros existentes en la tabla
      String q= "SELECT count(1) as total FROM "+ tabla + "";
      System.out.println(q);
      if(q=="0"){
          q="1";
      }
      
      //se ejecuta la consulta
      try{         
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
         System.out.println("Hay un total de "+registros+" registros");
      }catch(SQLException e){
         System.out.println(e);
      }
    //Se selecciona la tabla  envia los datos uno por vez
    Object[][] data = new String[registros][colName.length]; 
    String col[] = new String[colName.length];
    System.out.println(sql);
      try{         
         PreparedStatement pstm = connection.prepareStatement(sql);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){            
            for(int j=0; j<=colName.length-1;j++){
                col[j] = res.getString(colName[j]);
                data[i][j] = col[j];            
            }          
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
 
 /* METODO PARA REALIZAR SELECCIONAR LOS REGISTROS  QUE CONTENGAN UNA CADENA (LIKE EN SQL), Y VOLCARLOS EN UNA JTABLE
 * INPUT: 
  colName= nombtre de columna
  tabla=nombre de tabla
  value=cadena a buscar
 * OUTPUT: String con los datos concatenados
*/
 public Object [][] SelectTablaCondicion(String colName[],String tabla, String sql,String condicion){
     
      int registros = 0;
      //se arma la consulta 
      //obtenemos la cantidad de registros existentes en la tabla
      String q= "SELECT count(1) as total FROM "+ tabla +" WHERE "+condicion;
      System.out.println(q);
      
      if(q=="0"){
          q="1";
      }
      
      
      //se ejecuta la consulta
      try{         
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
         System.out.println(registros);
      }catch(SQLException e){
         System.out.println(e);
      }
    //Se selecciona la tabla  envia los datos uno por vez
    Object[][] data = new String[registros][colName.length]; 
    String col[] = new String[colName.length];
    System.out.println(sql);
      try{         
         PreparedStatement pstm = connection.prepareStatement(sql);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){            
            for(int j=0; j<=colName.length-1;j++){
                col[j] = res.getString(colName[j]);
                data[i][j] = col[j];            
            }          
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 } 
 // METODO PARA SELECCIONA UNA COLUMNA SIN CONDICION
 public Object [] SelectCol(String tabla, String colName, String col){
      int registros = 0;
      //se arma la consulta 
      //obtenemos la cantidad de registros existentes en la tabla
      String q= "SELECT count(1) as total FROM "+ tabla + "";
      System.out.println(q);
      //se ejecuta la consulta
      try{         
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
         System.out.println(registros);
      }catch(SQLException e){
         System.out.println(e);
      }
    //Se selecciona la tabla  envia los datos uno por vez
    Object[] data = new String[registros]; 
    String q2= "SELECT "+colName+" FROM "+ tabla + "";
    System.out.println(q2);
      try{         
         PreparedStatement pstm = connection.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){            
            data[i] = res.getString(col);
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 } 
 

  /* METODO PARA REALIZAR SELECCIONAR LOS REGISTROS DE UNA COLUMNA USANDO 1 FILTRO
 * INPUT: 
  colName= nombtre de columna
  tabla=nombre de tabla
  value=cadena de caracteres que se busca
  col= columna que se quiere seleccionar
 * OUTPUT: String con los datos concatenados
*/
 public Object [] Select1(String tabla, String colName, String value, String col){
      int registros = 0;
      //se arma la consulta 
      //obtenemos la cantidad de registros existentes en la tabla
      String q= "SELECT count(1) as total FROM "+ tabla + " WHERE "+colName+" = '"+value+"'";
      System.out.println(q);
      //se ejecuta la consulta
      try{         
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         registros = res.getInt("total");
         res.close();
         System.out.println(registros);
      }catch(SQLException e){
         System.out.println(e);
      }
    //Se selecciona la tabla  envia los datos uno por vez
    Object[] data = new String[registros]; 
    String q2= "SELECT * FROM "+ tabla + " WHERE "+colName+" = '"+value+"'";
    System.out.println(q2);
      try{         
         PreparedStatement pstm = connection.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){            
            data[i] = res.getString(col);
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 } 
 
  /* METODO PARA REALIZAR SELECCIONAR LOS REGISTROS DE UNA COLUMNA USANDO 2 FILTRO
 * INPUT: 
  colName= nombre de columna
 colName2= segundo nombre de columna
  tabla=nombre de tabla
  value=cadena de caracteres que se busca
 value2=segundacadena de caracteres que se busca
  col= columna que se quiere seleccionar
 col2= segunda columna que se quiere seleccionar
 * OUTPUT: String con los datos concatenados
*/
 public Object [] Select2(String tabla, String colName, String value,String colName2, String value2,String col){
      int registros = 0;
      //se arma la consulta 
      //obtenemos la cantidad de registros existentes en la tabla
      String q= "SELECT count(1) as total FROM "+ tabla + " WHERE "+colName+" = '"+value+"' AND "+colName2+" = '"+value2+"'";
      System.out.println(q);
      //se ejecuta la consulta
      try{         
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         registros = res.getInt("total");
         res.close();
         System.out.println(registros);
      }catch(SQLException e){
         System.out.println(e);
      }
    //Se selecciona la tabla  envia los datos uno por vez
    Object[] data = new String[registros]; 
    String q2= "SELECT * FROM "+ tabla + " WHERE "+colName+" = '"+value+"' AND "+colName2+" = '"+value2+"'";
    System.out.println(q2);
      try{         
         PreparedStatement pstm = connection.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){            
            data[i] = res.getString(col);
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
     
    return data;
 } 
  public void desconectar()
    {
        if(resultSet==null&&statement==null){
            try {
            connection.close();
            System.out.println("Desconectado de la base de datos [ " + this.bd + "]");
            }
            catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
                try {
                resultSet.close();
                statement.close();
                connection.close();
                System.out.println("Desconectado de la base de datos [ " + this.bd + "]");
                }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
        }
    }


 /* METODO PARA INSERTAR UN REGISTRO EN LA BASE DE DATOS
 * INPUT:
 table = Nombre de la tabla
 fields = String con los nombres de los campos donde insertar Ej.: campo1,campo2campo_n
 values = String con los datos de los campos a insertar Ej.: valor1, valor2, valor_n
 * OUTPUT:
 * Boolean
*/
 public boolean insertar(String table, String fields, String values)
    {
        boolean res=false;
        //Se arma la consulta
        String q="INSERT INTO " + table + " ( " + fields + " ) VALUES ( " + values + " ) ";
        //se ejecuta la consulta
        System.out.print(q);
        try {
            PreparedStatement pstm = connection.prepareStatement(q);
            pstm.execute();
            pstm.close(); 
            res=true;
         }catch(Exception e){
            System.out.println(e);
        }
      return res;
    }
 
 /*METODO PARA ACTUALIZAR UN REGISTRO
 INPUT
 sql= sentencia sql como String
 OUTPUT 
 Boolean
 */
 public boolean update(String sql)
    {
        boolean res=false;
        System.out.println(sql);
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = connection.prepareStatement(sql); 
            pstm.execute();
            pstm.close(); 
            res=true;
         }catch(Exception e){
            System.out.println(e);
        }
      return res;
    }
 
 

   /* METODO PARA SELECCIONAR UN DATO
 * INPUT:
 tabla = Nombre de la tabla
 colName = String con el nombre de columna donde se busca
 col = String con el nombre de la columna del dato a recuperar
 value = String con el dato a buscar
 * OUTPUT:
 * Boolean
*/
public String SelectDato (String tabla, String colName, String value, String col){
    //Se selecciona la tabla  envia los datos uno por vez
    String data = new String();
    String q2= "SELECT "+col+" FROM "+ tabla + " WHERE "+colName+" = "+value+"";
    System.out.println("DATO: "+q2);
      try{         
         PreparedStatement pstm = connection.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         res.next();
         data = res.getString(col);
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    System.out.println(data);
    return data;
 } 


/* METODO PARA REALIZAR SELECCIONAR EL MAXIMO VALOR DE UNA COLUMNA
 * INPUT: 
  colName= nombtre de columna
  tabla=nombre de tabla

 * OUTPUT: String con los datos concatenados
*/
 public Object [] SelectMax(String tabla, String colName){
      int registros = 0;
      //se le asigna a data un solo registro y se arma la consulta 
     
    Object[] data = new String[1]; 
    String q= "SELECT MAX("+colName+") AS "+colName+" FROM "+tabla+"";
    System.out.println(q);
      try{         
         PreparedStatement pstm = connection.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){            
            data[i] = res.getString(colName);
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println("VACIO");
        data[0]= "0";
    }
    return data;
 } 

 /* METODO PARA REALIZAR LA SUMA DE UNA COLUMNA
 * INPUT: 
  colName= nombtre de columna
  tabla=nombre de tabla
  sql= condicion

 * OUTPUT: String con los datos concatenados
*/
public String Sumar (String tabla, String colName, String sql){
    //Se selecciona la tabla  envia los datos uno por vez
    String data = new String();
    String q2= "SELECT SUM("+colName+") FROM "+ tabla + " WHERE "+sql+"";
    System.out.println("DATO"+q2);
      try{         
         PreparedStatement pstm = connection.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         res.next();
         data = res.getString("SUM("+colName+")");
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    System.out.println(data);
    return data;
 }     
}


