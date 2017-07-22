package application;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaSource;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAppDesignCheckApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringAppDesignCheckApplication.class, args);        

        try {
            
            JavaProjectBuilder jpb = new JavaProjectBuilder();
            jpb.addSourceTree(new File("/home/skuarch/JavaProjects/SpringAppDesignCheck/src/main/java/application/IndexController.java"));
            
            List<JavaClass> classes = jpb.getClasses()
                    .stream()
                    .collect(Collectors.toList());
            
            
            for (JavaClass clas : classes) {
                List<String> imports = clas.getSource().getImports();
                
                for (String i : imports) {
                
                    System.out.println("imports "  + i);
                    // is rest controller org.springframework.web.bind.annotation.RestController
                    
                }
                
                
                List<JavaField> fields = clas.getFields();
                for (JavaField field : fields) {                        
                    System.out.println("fields  "  + field.getType().getFullyQualifiedName());                    
                    List<String> impr = field.getType().getSource().getImports();
                    
                    for (String string : impr) {
                        System.out.println("a ver si este " + string);
                    }
                 
                    // esta es la buena
                    String otherName = field.getType().getCanonicalName();
                    System.out.println("el pinche name "+ otherName);
                    JavaProjectBuilder jpb2 = new JavaProjectBuilder();
                    //jpb2.addSource(new File("/home/skuarch/JavaProjects/SpringAppDesignCheck/src/main/java/" + ));                    
                    
                    
                    List<JavaAnnotation> anotentions = field.getAnnotations();
                    
                    for (JavaAnnotation anotention : anotentions) {
                        System.out.println("ano " + field.getName() + " " + anotention.getType().getPackageName());   
                        List<String> imp = field.getType().getSource().getImports();
                        
                        for (String im : imp) {
                            
                            System.out.println("este es la buena " + im);
                            
                        }
                        
                    }
                    
                    if(field.getType().isA("org.springframework.data.jpa.repository.JpaRepository")){
                        System.out.println("tiene un repo");
                    }
                }
            }
            
            
            
            /*JavaDocBuilder builder = new JavaDocBuilder();
            builder.addSource(new File("/home/skuarch/JavaProjects/SpringAppDesignCheck/src/main/java/application/SpringAppDesignCheckApplication.java"));
            JavaSource src = builder.getSources()[0];   
            
            URL url = src.getURL();
            String imports[] = src.getImports();
            JavaPackage pkg = src.getPackage();
            String name = pkg.getName();
            String toString = pkg.toString();
            //JavaPackage parent = pkg.getParentPackage();
            JavaClass[] classes = pkg.getClasses();
            JavaMethod[] methods = classes[0].getMethods();
            for (JavaMethod method : methods) {
                System.out.println("Method Name : " + method.getName());
            }
            System.out.println("pkg name : " + name);
            System.out.println("pkg to String : " + toString);
            //System.out.println("pkg parent name : " + parent);
            System.out.println("imports " + imports.length);
            System.out.println("url " + url.toString());*/
            
        } catch (Exception e) {
            throw e;
        }

    }
}
