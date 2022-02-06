package es.adriansoriagarcia.proyectofinaljava1;

import java.net.URISyntaxException;
import java.net.URL;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    int backGroundX = 0 ;//variable posicion x imágen fondo
    int backGroundX1 = 1200 ;//variable posicion x imágen fondo1
    int dirXCactusSim;//variable posicion x cactus simple
    int dirXCactusDob;//variable posicion x cactus doble
    int posYJug = 330;//variable posicion y jugador
    final int TEXT_SIZE = 24;//constante para tamaño de fuente del texto de puntuación.
    final int SCENE_TAM_X = 780;//constante para el tamaño horizontal de la ventana del juego.
    final int SCENE_TAM_Y = 460;//constante para el tamaño vertical de la ventana del juego.
    int score = 0;//Puntuación actual
    int highScore = 0;//Puntuación máxima
    Timeline animationGround;//Declaración de timeline
    Scene scene;//Declaración de scene
    Text textScore;//Declaración de text de puntos
    Text textHighScore;//Declaración de text de puntos maximo
    byte velocidadSalto;//variable de velocidad del jugador
    Label gameOver;//Declaración de label gameOver colision 1
    Label gameOver1;//Declaración de label gameOver colision 2
    boolean finPartida;//variable boleana para el fin de partida
    Group GroupReinicio;//Declaración de grupo iconreinicio para colision 1
    Group GroupReinicio1;//Declaración de grupo iconreinicio para colision 2
    Timeline dinoMovimiento;//Declaración timeline de movimiento de imagenes jugador
    byte contador = 0;//variable para contador de imagenes

   
    
    @Override
    
    public void start(Stage primaryStage ) {
        // Panel principal que contendrá los elementos de la pantalla
        Pane paneRoot = new Pane();
        
        //Tamaño de la ventana
        scene = new Scene(paneRoot, SCENE_TAM_X, SCENE_TAM_Y, Color.WHITE);  
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("T-Rex Game");
        
        //Llamada al método layout pasando el parámetro paneRoot 
        layout(paneRoot);
        
        

    //--------------------------------------------------------------------------------------  
        //BLOQUE PARA T-REX
    //--------------------------------------------------------------------------------------  
        // Cargar la imagen crear T-Rex ImageView
        Image img = new Image(getClass().getResourceAsStream("/images/Dino-left.png"));
        ImageView imgView = new ImageView(img);
        Image imgDin = new Image(getClass().getResourceAsStream("/images/Dino-right.png"));
        ImageView imgViewDin = new ImageView(imgDin);
        
        //Creación del rectangulo para englobar imágen
        Rectangle rectTrex = new Rectangle(42,45);

        //Creacion del grupo para añadir la imágen y el rectangulo
        Group groupTrex = new Group();
        groupTrex.getChildren().addAll(imgView,imgViewDin,rectTrex);
        groupTrex.setLayoutX(30);
        groupTrex.setLayoutY(posYJug);
        //Hacer invisible el rectangulo
        rectTrex.setVisible(false);
        // Añadir el grupo al panel principal de la pantalla
        paneRoot.getChildren().add(groupTrex);
    //--------------------------------------------------------------------------------------  
        
    //--------------------------------------------------------------------------------------  
        //BLOQUE PARA IMAGEN DE FONDO
    //--------------------------------------------------------------------------------------    
        // Cargar la imagen Imagenfondo1
        Image imgFon = new Image(getClass().getResourceAsStream("/images/Ground.png"));
        ImageView imgViewFon = new ImageView(imgFon);
        imgViewFon.setY(370);
        // Añadir el ImageView al panel principal de la pantalla
        paneRoot.getChildren().add(imgViewFon);
            
        // Cargar la imagen Imagenfondo2
        ImageView imgViewFon1 = new ImageView(imgFon);
        imgViewFon1.setY(370);
        // Añadir el ImageView al panel principal de la pantalla
        paneRoot.getChildren().add(imgViewFon1);
    //---------------------------------------------------------------------------------------    

    //--------------------------------------------------------------------------------------  
        //BLOQUE PARA OBSTACULOS
    //--------------------------------------------------------------------------------------    
        // Crear cactus simple
        Rectangle rectSim1 = new Rectangle(10,40);
        rectSim1.setX(200);
        rectSim1.setY(335);
        Rectangle rectSim2 = new Rectangle(5,15);
        rectSim2.setX(190);
        rectSim2.setY(345);
        Rectangle rectSim3 = new Rectangle(5,15);
        rectSim3.setX(215);
        rectSim3.setY(340);
        Rectangle rectSim4 = new Rectangle(5,5);
        rectSim4.setX(195);
        rectSim4.setY(355);
        Rectangle rectSim5 = new Rectangle(5,5);
        rectSim5.setX(210);
        rectSim5.setY(350);
        Rectangle collisionrectangle1 = new Rectangle(30,40);
        collisionrectangle1.setX(190);
        collisionrectangle1.setY(335);
        collisionrectangle1.setVisible(false);
        Group grouprectSim1 = new Group();
        grouprectSim1.getChildren().addAll(rectSim1,rectSim2,rectSim3,rectSim4,rectSim5,collisionrectangle1);

        // Añadir el grupo al panel principal de la pantalla
        paneRoot.getChildren().add(grouprectSim1);
        
        // Crear cactus doble
        Rectangle rectDob1 = new Rectangle(10,40);
        rectDob1.setX(400);
        rectDob1.setY(335);
        Rectangle rectDob2 = new Rectangle(5,15);
        rectDob2.setX(390);
        rectDob2.setY(340);
        Rectangle rectDob3 = new Rectangle(5,15);
        rectDob3.setX(415);
        rectDob3.setY(340);
        Rectangle rectDob4 = new Rectangle(5,5);
        rectDob4.setX(395);
        rectDob4.setY(350);
        Rectangle rectDob5 = new Rectangle(5,5);
        rectDob5.setX(410);
        rectDob5.setY(350);
        //--------------------------------------------------------------------
        Rectangle rectDob21 = new Rectangle(10,40);
        rectDob21.setX(415);
        rectDob21.setY(335);
        Rectangle rectDob23 = new Rectangle(5,15);
        rectDob23.setX(430);
        rectDob23.setY(340);
        Rectangle rectDob25 = new Rectangle(5,5);
        rectDob25.setX(425);
        rectDob25.setY(350);
        Rectangle collisionrectangle2 = new Rectangle(45,40);
        collisionrectangle2.setX(390);
        collisionrectangle2.setY(335);
        collisionrectangle2.setVisible(false);
        Group grouprectDob1 = new Group();
        grouprectDob1.getChildren().addAll(rectDob1,rectDob2,rectDob3,rectDob4,rectDob5,rectDob21,rectDob23,rectDob25,collisionrectangle2);
        // Añadir el grupo al panel principal de la pantalla
        paneRoot.getChildren().add(grouprectDob1);  
    //---------------------------------------------------------------------------------------     
        animationGround = new Timeline(      
                new KeyFrame(Duration.seconds(0.017),(ActionEvent ae) -> {
                    posYJug += velocidadSalto;
                    //System.out.println(posYJug);
                    score++;
                    textScore.setText(String.valueOf(score));    

                    //Bloque repetición cactus simple    
                    grouprectSim1.setLayoutX(dirXCactusSim);    
                    
                    if(dirXCactusSim <= -220){
                        dirXCactusSim = 1200;
                    }

                    //Bloque repetición cactus doble    
                    grouprectDob1.setLayoutX(dirXCactusDob);    
                    //System.out.println(dirXCactusSim);

                    if(dirXCactusDob <= -420){
                        dirXCactusDob = 1200;
                    }

                    //Bloque repetición imagen fondo
                    imgViewFon.setX(backGroundX);
                    imgViewFon1.setX(backGroundX1);

                    System.out.println("posición de imágen fondo "+backGroundX);
                    System.out.println("posición de imágen fondo 1"+backGroundX1);
                    
                    if(backGroundX == 0){
                        backGroundX1 = 1200;
                    }

                    if(backGroundX1 == 0) {
                       backGroundX = 1200; 
                    }

                    
                    //Bloque para velocidad de fondo
                    if(score < 400){
                        dirXCactusSim -= 4;
                        dirXCactusDob -= 4;
                        backGroundX -=4;
                        backGroundX1 -=4;
                    }
                    if(score >= 400){
                        dirXCactusSim -= 6;
                        dirXCactusDob -= 6;
                        backGroundX -=6;
                        backGroundX1 -=6;
                    }
                    /*if(score >= 800){
                        dirXCactusSim -= 8;
                        dirXCactusDob -= 8;
                        backGroundX -=8;
                        backGroundX1 -=8;
                    }*/
                    
                    
                    

                    //bloque salto T-Rex
                    groupTrex.setLayoutY(posYJug);
                    System.out.println("posición deljugador "+posYJug);
                        scene.setOnKeyPressed((KeyEvent event) -> {
                         switch(event.getCode()){
                             case SPACE:
                                 //Pulsa tecla espacio

                                 if(posYJug == 330) {
                                    velocidadSalto = -4;
                                 }            
      
                                URL urlAudio = getClass().getResource("/audio/salto.mp3");
                                if(urlAudio != null) {
                                    try {
                                        AudioClip audioClip1 = new AudioClip(urlAudio.toURI().toString());
                                        audioClip1.play();
                                    } catch (URISyntaxException ex) {
                                        System.out.println("Error en el formato de ruta de archivo de audio");
                                    }            
                                } else {
                                    System.out.println("No se ha encontrado el archivo de audio");
                                }
                                 break;

                         }
                     });

                     scene.setOnKeyReleased((KeyEvent event) -> {
                         
                     }); 

                    //Llamada al método colision pasando varios parámetros
                    colision(collisionrectangle1, rectTrex, collisionrectangle2, paneRoot);
                    
                    //SALTOS DEL JUGADOR
                    if(posYJug == 242) {
                        velocidadSalto = 4;
                    }
                    if(posYJug == 330) {
                        velocidadSalto = 0;
                    }
      
                })

            );   
        //---------------------------------------------------------------------------------------   
            //TIMELINE PARA EL CAMBIO DE IMAGEN DEL JUGADOR
        //---------------------------------------------------------------------------------------  
        
        dinoMovimiento = new Timeline(      
                new KeyFrame(Duration.seconds(0.300),(ActionEvent ae) -> {
                        contador++;
                        System.out.println(contador);
                        switch(contador) {
			case 1:
				imgView.setVisible(true); 
                                imgViewDin.setVisible(false); 
                                System.out.println("entra en 1");
                            break;    
			case 2:
				imgViewDin.setVisible(true);
                                imgView.setVisible(false); 
                                System.out.println("entra en 2");
                             break;   
			case 3:
				imgView.setVisible(true); 
                                imgViewDin.setVisible(false);
                                System.out.println("entra en 3");
                             break;   
			case 4:
				imgViewDin.setVisible(true);
                                imgView.setVisible(false);
                                System.out.println("entra en 4");
                            break;    
                        }
                    //if para que repita siempre los números del 1 al 4 en el contador
                    if(contador==4){
                        contador=0;
                    }
                    
                })
                
            );   
        
        dinoMovimiento.setCycleCount(Timeline.INDEFINITE);  
        animationGround.setCycleCount(Timeline.INDEFINITE);
        running();
            
 
        
    }
    public void reset() {
        score = 0;
        dirXCactusSim=0;
        dirXCactusDob=0;
        System.out.println("reset");
        running();
        

    }
    
    public void running(){
        scene.setOnKeyPressed((KeyEvent event) -> {
                     switch(event.getCode()){
                         case SPACE:
                             //Pulsa tecla espacio
                             //para iniciar partida
                             animationGround.play();
                             dinoMovimiento.play();
                             backGroundX=0;
                             backGroundX1=1200;
                             finPartida=false;
                             if(finPartida == false) {
                              GroupReinicio1.setVisible(false);
                              GroupReinicio.setVisible(false); 
                             }
                          break;
                     }
         }); 
    }
    
    private void colision(Rectangle collisionrectangle1, Rectangle rectTrex, Rectangle collisionrectangle2, Pane paneRoot) {
        //Bloque de colisiones del T-Rex con los cactus
        Shape zonaColision=Shape.intersect(collisionrectangle1, rectTrex);
        boolean colisionVacia = zonaColision.getBoundsInLocal().isEmpty();
        if(colisionVacia == false) {
          System.out.println("colision cactus simple");
          animationGround.stop();
          dinoMovimiento.stop();
          finPartida=true;
          if(finPartida == true) {
            //finPartida(paneRoot);
            gameOver = new Label("GAME OVER");
            gameOver.setFont(Font.font(TEXT_SIZE));
            gameOver.setMinWidth(SCENE_TAM_X);
            gameOver.setTranslateY(50);
            gameOver.setAlignment(Pos.CENTER);
            
            Image imgRein = new Image(getClass().getResourceAsStream("/images/reiniciar.png"));
            ImageView imgViewRei = new ImageView(imgRein);
            imgViewRei.setY(100);
            imgViewRei.setX(375);
            // Añadir el ImageView al panel principal de la pantalla
            GroupReinicio = new Group();
            GroupReinicio.getChildren().addAll(imgViewRei,gameOver);
            paneRoot.getChildren().add(GroupReinicio); 
          }
          
          //Bloque insertar audio de colisión
          URL urlAudio = getClass().getResource("/audio/game over.mp3");
          if(urlAudio != null) {
            try {
                AudioClip audioClip1 = new AudioClip(urlAudio.toURI().toString());
                audioClip1.play();
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
           } else {
                System.out.println("No se ha encontrado el archivo de audio");
           }
          if(score > highScore) {
              //cambiar a nueva puntuación más alta
              highScore=score;
              textHighScore.setText(String.valueOf(highScore));
          }
          reset();
        }  

        Shape zonaColision2 = Shape.intersect(collisionrectangle2, rectTrex);
        boolean colisionVacia2 = zonaColision2.getBoundsInLocal().isEmpty();
        if(colisionVacia2 == false) {
          System.out.println("colision cactus doble");
          animationGround.stop();
          dinoMovimiento.stop();
          finPartida=true;
          if(finPartida == true) {
            //finPartida(paneRoot);
            gameOver1 = new Label("GAME OVER");
            gameOver1.setFont(Font.font(TEXT_SIZE));
            gameOver1.setMinWidth(SCENE_TAM_X);
            gameOver1.setTranslateY(50);
            gameOver1.setAlignment(Pos.CENTER);
            Image imgRein1 = new Image(getClass().getResourceAsStream("/images/reiniciar.png"));
            ImageView imgViewRei1 = new ImageView(imgRein1);
            imgViewRei1.setY(100);
            imgViewRei1.setX(375);
            // Añadir el ImageView al panel principal de la pantalla
            GroupReinicio1 = new Group();
            GroupReinicio1.getChildren().addAll(imgViewRei1,gameOver1);
            paneRoot.getChildren().add(GroupReinicio1); 
          }
          
          //Bloque insertar audio de colisión
          URL urlAudio = getClass().getResource("/audio/game over.mp3");
          if(urlAudio != null) {
            try {
                AudioClip audioClip1 = new AudioClip(urlAudio.toURI().toString());
                audioClip1.play();
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
           } else {
                System.out.println("No se ha encontrado el archivo de audio");
           }
          //si la nueva puntuación es mayor a la máxima conseguida se actualiza.
          if(score > highScore) {
              //cambiar a nueva puntuación más alta
              highScore=score;
              textHighScore.setText(String.valueOf(highScore));
          }
          reset();
        } 
    }
    
    private void layout(Pane paneRoot){
        //LAYOUTS PARA MOSTRAR PUNTUACIONES
        //Layout principal
        HBox paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setMinWidth(SCENE_TAM_X);
        paneScores.setAlignment(Pos.TOP_RIGHT);
        paneScores.setSpacing(50);
        paneRoot.getChildren().add(paneScores);
        //Layout para puntuación actual
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScore);
        //Layout para puntuación máxima
        HBox paneHighScore = new HBox();
        paneHighScore.setSpacing(10);
        paneScores.getChildren().add(paneHighScore);
        //Texto de etiqueta para la puntuación
        Text texTitleScore = new Text("HI");
        texTitleScore.setFont(Font.font(TEXT_SIZE));
        texTitleScore.setFill(Color.BLACK);
        //Texto para la puntuación
        textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.BLACK);
        //Texto de etiqueta para la puntuación máxima
        Text textTitleHighScore = new Text("");
        textTitleHighScore.setFont(Font.font(TEXT_SIZE));
        textTitleHighScore.setFill(Color.BLACK);
        //Texto para la puntuación máxima
        textHighScore = new Text("0");
        textHighScore.setFont(Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.BLACK);
        //Añadir los textos a los layouts reservados para ellos
        paneCurrentScore.getChildren().add(texTitleScore);
        paneCurrentScore.getChildren().add(textScore);
        paneHighScore.getChildren().add(textTitleHighScore);
        paneHighScore.getChildren().add(textHighScore);
    }

    public static void main(String[] args) {
        launch();
    }

}