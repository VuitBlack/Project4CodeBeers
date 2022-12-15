package codebeers.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertarCliente {

    public static void main(String[] args){
        //Creamos el objeto SessionFactory para leer el archivo de configuración y construir el SessionFactory
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes_ORM.class).buildSessionFactory();
        //Creamos el objeto de tipo Session
        Session mySession = myFactory.openSession();

        try{
            //Creo un Cliente ORM
            Clientes_ORM cliente1 = new Clientes_ORM("pajaroloco@email.com","Woody","Pino de la esquina","8888P","Premium");
            mySession.beginTransaction();           //Comenzamos la transacción para guardar el objeto Clientes en la BBDD
            mySession.save(cliente1);               //Guarda el objeto cliente en BBDD
//            mySession.update(cliente1);             //Actualiza el objeto cliente en la BBDD
//            mySession.delete(cliente1);             //Borra el objeto cliente en la BBDD
            mySession.getTransaction().commit();    //Mediante el Commit se graba en la base de datos
            System.out.println("Registro insertado correcatamente");
            mySession.close();                      //Se cierra la sesion para liberar memoria
        }finally {
            myFactory.close();  //siempre cerrar la conexión para liberar memoria
        }

    }
}
