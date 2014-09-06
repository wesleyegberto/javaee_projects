Java EE Projects
===============

Repository for Java EE projects to test the plataform.
To test I've created a ToyShop application using different technologies of Java EE.

* ToyShop US.txt: user stories used of the features of the application;
* ToyShopDbScript.sql: script of database with tables for Realm, inserts of data and so on.


### ToyShop Faces ###
Project using: JSF (with bean validation), EJB, JPA, JAAS and JAX-WS/RS.

* JSF: presentation layer;
* JPA: model layer;
* EJB: business layer;
* Web service: layer to allow other applications integrate with the ToyShop.


### ToyShop Servlets ###
Project using: Servlets, JSP, EJB, JPA and JAAS.

* Servlets/JSP: presentation layer;
* JPA: model layer;
* EJB: business layer.


### JDBCRealm ###
> Tomcat - server.xml:
<Realm className="org.apache.catalina.realm.JDBCRealm" debug="99" driverName="com.mysql.jdbc.Driver"
	connectionURL="jdbc:mysql://localhost/DbWithTables?user=root&amp;password=123123"
   	userTable="USER" userNameCol="username" userCredCol="password" userRoleTable="ROLE" roleNameCol="rolename" />


> JBoss - standalone.xml:
  . In <security-domain> named as "other" include this in <authentication> node: <login-module code="UsersRoles" flag="required"/>
  . Then include this node in <security-domains>
<security-domain name="ToyShopRealm" cache-type="default">
    <authentication>
        <login-module code="Database" flag="required">
            <module-option name="dsJndiName" value="java:/jdbc/DataSourceToDB"/>
            <module-option name="principalsQuery" value="select password from USER where username=?"/>
            <module-option name="rolesQuery" value="select rolename,'Roles' from USER_ACL where username=?"/>
            <module-option name="hashAlgorithm" value="MD5"/>
            <module-option name="hashEncoding" value="base64"/>
            <module-option name="unauthenticatedIdentity" value="Guest"/>
        </login-module>
    </authentication>
</security-domain>


### To Do ###
* Include a support chat using WebSockets


### Links ###
* (Bootstrap) [http://getbootstrap.com/]
* (JBoss JDBCRealm) [http://blog.amatya.net/2012/09/implementing-security-with-jaas-on.html]
* (Glassfish JDBCRealm) [http://java.dzone.com/articles/jdbc-realm-and-form-based]

