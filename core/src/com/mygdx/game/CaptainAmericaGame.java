package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Random;

import sun.rmi.runtime.Log;

public class CaptainAmericaGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgBackgroung1, imgBackgroung3, enemigo1, fDerecha, fIzquierda, fArriba, bEscudo, bMartillo, personajeEscudo, personajeEscudo2, bala, vidaImagen, capiFin;
    Texture [] animPerson, animPerson2, animPersonEscudoIda, animPersonEscudoVuelta, animEscudoIda, animEscudoVuelta, enemigo1Caido, animAgua;
	Texture personaje, personaje2, saltoDerecha, saltoIzquierda, martilloDerecha, martilloIzquierda, rayos;
	int contadorPerson, contadorPerson2, contadorPersonEscudoIda, contadorPersonEscudoVuelta,contadorEscudoIda, contadorEscudoVuelta, personFrame, personFrame2, PersonEscudoIdaFrame, PersonEscudoVueltaFrame,EscudoIdaFrame, EscudoVueltaFrame, x, xEscudo, escudo, ySalto, contadorBalas, vidaPersonaje, vidaEnemigo, contadorColisionEnemigo, contadorEnemigoCaido, EnemigoCaidoFrame, contadorAgua1, contadorAgua2, contadorAgua3, aguaFrame1, aguaFrame2, aguaFrame3;
    boolean ida, vuelta, izquierda, derecha, personajeMuerto, enemigo1Muerto, finJuego, isPersonaje;
    ArrayList<Integer> balasXs, balasYs;
    ArrayList<Rectangle> balasRectangles = new ArrayList<Rectangle>();
    Rectangle manRectangle, enemigoRectangle, escudoRectangle, rayosRectangle;
    Random random ;

    @Override
	public void create () {
		batch = new SpriteBatch();

		//Fondo pantalla
        imgBackgroung1 = new Texture("fondo1B.png");

        //Imagen vidas
        vidaImagen = new Texture("Image16.png");

        //Personaje quieto derecha
        personaje = new Texture("Image15.png");
        //Personaje quieto izquierda
        personaje2 = new Texture("Image15B.png");

        saltoDerecha = new Texture("Image141.png");
        saltoIzquierda = new Texture("Image141B.png");

        //Personaje con escudo derecha
        personajeEscudo= new Texture("Image156.png");
        //Personaje con escudo izquierda
        personajeEscudo2= new Texture("Image156B.png");

        enemigo1 = new Texture("Image61.png");
        bala = new Texture("Image251.png");

        //animacion personaje derecha
		animPerson = new Texture[11];
        animPerson[0] = new Texture("Image129.png");
        animPerson[1] = new Texture("Image130.png");
        animPerson[2] = new Texture("Image131.png");
        animPerson[3] = new Texture("Image132.png");
        animPerson[4] = new Texture("Image133.png");
        animPerson[5] = new Texture("Image134.png");
        animPerson[6] = new Texture("Image135.png");
        animPerson[7] = new Texture("Image136.png");
        animPerson[8] = new Texture("Image137.png");
        animPerson[9] = new Texture("Image138.png");
        animPerson[10] = new Texture("Image139.png");

        //animacion personaje izquierda
        animPerson2 = new Texture[11];
        animPerson2[0] = new Texture("Image129B.png");
        animPerson2[1] = new Texture("Image130B.png");
        animPerson2[2] = new Texture("Image131B.png");
        animPerson2[3] = new Texture("Image132B.png");
        animPerson2[4] = new Texture("Image133B.png");
        animPerson2[5] = new Texture("Image134B.png");
        animPerson2[6] = new Texture("Image135B.png");
        animPerson2[7] = new Texture("Image136B.png");
        animPerson2[8] = new Texture("Image137B.png");
        animPerson2[9] = new Texture("Image138B.png");
        animPerson2[10] = new Texture("Image139B.png");

        //animacion personaje al soltar el escudo derecha
		animPersonEscudoIda = new Texture[3];
        animPersonEscudoIda[0] = new Texture("Image145.png");
        animPersonEscudoIda[1] = new Texture("Image156.png");
        animPersonEscudoIda[2] = new Texture("Image15.png");

        //animacion personaje al soltar el escudo izquierda
        animPersonEscudoVuelta = new Texture[3];
        animPersonEscudoVuelta[0] = new Texture("Image156.png");
        animPersonEscudoVuelta[1] = new Texture("Image145.png");
        animPersonEscudoVuelta[2] = new Texture("Image15.png");

        //Lanzamiento escudo
        animEscudoIda = new Texture[2];
        animEscudoIda[0] = new Texture("Image120.png");
        animEscudoIda[1] = new Texture("Image121.png");

        //Vuelta escudo
        animEscudoVuelta = new Texture[2];
        animEscudoVuelta[0] = new Texture("Image121.png");
        animEscudoVuelta[1] = new Texture("Image120.png");

        //Animacion Enemigo Caido
        enemigo1Caido = new Texture[2];
        enemigo1Caido[0] = new Texture("Image168.png");
        enemigo1Caido[1] = new Texture("Image169.png");

        //Botones
        fDerecha = new Texture("flechader.png");
        fIzquierda = new Texture("flechaizq.png");
        fArriba = new Texture("flechaarriba.png");
        bEscudo = new Texture("Image120.png");
        bMartillo = new Texture("Image85.png");

        //Martillo
        martilloDerecha = new Texture("Image141Martillo.png");
        martilloIzquierda = new Texture("Image141MartilloB.png");

        rayos = new Texture("Image97.png");

        //Muerte personaje
        capiFin = new Texture("Image155.png");

        //animacion agua
        animAgua = new Texture[3];
        animAgua[0] = new Texture("Image173.png");
        animAgua[1] = new Texture("Image174.png");
        animAgua[2] = new Texture("Image175.png");

        contadorPerson = 0;
        contadorPerson2 = 0;
        contadorPersonEscudoIda = 0;
        contadorPersonEscudoVuelta = 0;
        contadorEscudoIda = 0;
        contadorEscudoVuelta = 0;
        contadorBalas = 0;
        contadorAgua1=0;
        contadorAgua2=1;
        contadorAgua3=2;

		personFrame = 0;
        personFrame2 = 0;
        PersonEscudoIdaFrame = 0;
        PersonEscudoVueltaFrame = 0;
        EscudoIdaFrame = 0;
        EscudoVueltaFrame = 0;
        EnemigoCaidoFrame = 0;
        aguaFrame1=0;
        aguaFrame2 = 1;
        aguaFrame3 = 2;

        x = 100;
		ySalto = 330;
		xEscudo = x +25;

		//Estado escudo
		escudo=0;

		vidaPersonaje = 400;
        vidaEnemigo = 350;

        //Verificar hacia donde va el escudo
		ida=true;
		vuelta=false;
		//Verificar hacia donde va el personaje
		izquierda = false;
		derecha = true;
		//Verificar la vida de los personajes
		personajeMuerto = false;
		enemigo1Muerto = false;
		//verificar el estado del juego
		finJuego = false;
		isPersonaje = false;

		//Balas
        balasXs = new ArrayList<Integer>();
        balasYs = new ArrayList<Integer>();
        random = new Random();

        contadorColisionEnemigo = 0;

	}

	@Override
	public void render () {
        batch.begin();

        contadorPerson++;
        contadorPerson2++;
        contadorPersonEscudoIda++;
        contadorPersonEscudoVuelta++;
        contadorEscudoIda++;
        contadorEscudoVuelta++;
        contadorEnemigoCaido++;
        contadorAgua1++;
        contadorAgua2++;
        contadorAgua3++;

        //Ajustar tiempo y modificar frames Personaje derecha
        contadorPerson = contadorPerson % 5;
        if (contadorPerson == 0) {

            personFrame++;

            personFrame = personFrame % 10;

        }

        //Ajustar tiempo y modificar frames Personaje izquierda
        contadorPerson2 = contadorPerson2 % 5;
        if (contadorPerson2 == 0) {

            personFrame2++;

            personFrame2 = personFrame2 % 10;

        }

        //Ajustar tiempo y modificar frames Personaje ida salida escudo
        contadorPersonEscudoIda = contadorPersonEscudoIda % 10;
        if (contadorPersonEscudoIda == 0) {

            PersonEscudoIdaFrame++;

            PersonEscudoIdaFrame = PersonEscudoIdaFrame % 3;

        }

        //Ajustar tiempo y modificar frames Personaje vuelta retorno escudo
        contadorPersonEscudoVuelta = contadorPersonEscudoVuelta % 10;
        if (contadorPersonEscudoVuelta == 0) {

            PersonEscudoVueltaFrame++;

            PersonEscudoVueltaFrame = PersonEscudoVueltaFrame % 3;

        }

        //Ajustar tiempo y modificar frames Personaje ida escudo
        contadorEscudoIda = contadorEscudoIda % 5;
        if (contadorEscudoIda == 0) {

            EscudoIdaFrame++;

            EscudoIdaFrame = EscudoIdaFrame % 2;

        }

        //Ajustar tiempo y modificar frames Personaje vuelta escudo
        contadorEscudoVuelta = contadorEscudoVuelta % 7;
        if (contadorEscudoVuelta == 0) {

            EscudoVueltaFrame++;

            EscudoVueltaFrame = EscudoVueltaFrame % 2;

        }

        //Ajustar tiempo y modificar frames enemigo caidp
        contadorEnemigoCaido = contadorEnemigoCaido % 7;
          if (contadorEnemigoCaido == 0) {

            EnemigoCaidoFrame++;

            EnemigoCaidoFrame = EnemigoCaidoFrame % 2;

       }

        //Ajustar tiempo y modificar frames agua1
        contadorAgua1 = contadorAgua1 % 7;
        if (contadorAgua1 == 0) {

            aguaFrame1++;

            aguaFrame1 = aguaFrame1 % 2;

        }

        //Ajustar tiempo y modificar frames agua2
        contadorAgua2 = contadorAgua2 % 7;
        if (contadorAgua2 == 0) {

            aguaFrame2++;

            aguaFrame2 = aguaFrame2 % 2;

        }

        //Ajustar tiempo y modificar frames agua3
        contadorAgua3 = contadorAgua3 % 7;
        if (contadorAgua3 == 0) {

            aguaFrame3++;

            aguaFrame3 = aguaFrame3 % 2;

        }

        //Generar Balas
        contadorBalas++;
        contadorBalas = contadorBalas%500;
        if(contadorBalas == 0){
            generarBala();
        }

        if(!finJuego) {

            batch.draw(imgBackgroung1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            batch.draw(animAgua[aguaFrame1], 0, 10, 150, 180);
            batch.draw(animAgua[aguaFrame2], 150, 10, 150, 180);
            batch.draw(animAgua[aguaFrame3], 300, 10, 150, 180);
            batch.draw(animAgua[aguaFrame1], 450, 10, 150, 180);
            batch.draw(animAgua[aguaFrame2], 600, 10, 150, 180);
            batch.draw(animAgua[aguaFrame3], 750, 10, 150, 180);
            batch.draw(animAgua[aguaFrame1], 900, 10, 150, 180);
            batch.draw(animAgua[aguaFrame2], 1050, 10, 150, 180);
            batch.draw(animAgua[aguaFrame3], 1200, 10, 150, 180);
            batch.draw(animAgua[aguaFrame1], 1350, 10, 150, 180);
            batch.draw(animAgua[aguaFrame2], 1500, 10, 150, 180);
            batch.draw(animAgua[aguaFrame2], 1650, 10, 150, 180);
            batch.draw(animAgua[aguaFrame2], 1800, 10, 150, 180);

            batch.draw(fDerecha, 500, 50, 180, 180);
            batch.draw(fIzquierda, 100, 50, 180, 180);
            batch.draw(fArriba, 300, 50, 180, 180);
            batch.draw(bEscudo, 1500, 50, 180, 180);
            batch.draw(bMartillo, 1700, 50, 180, 180);

            //Control Vidas
            if(vidaPersonaje == 400 || vidaPersonaje > 250){
                batch.draw(vidaImagen, 50, 950, 120, 120);
                batch.draw(vidaImagen, 200, 950, 120, 120);
                batch.draw(vidaImagen, 350, 950, 120, 120);
            } else if(vidaPersonaje == 250 || vidaPersonaje > 100){
                batch.draw(vidaImagen, 50, 950, 120, 120);
                batch.draw(vidaImagen, 200, 950, 120, 120);
            } else if(vidaPersonaje == 100 || vidaPersonaje > 0){
                batch.draw(vidaImagen, 50, 950, 120, 120);
            }

            //Verificamos si el enemigo no a caido
            if (!enemigo1Muerto) {
                batch.draw(enemigo1, 1250, 330, 200, 200);
            }

            //Dibujar balas y añadir rectangulo para colisiones
            balasRectangles.clear();
            for (int i = 0; i < balasXs.size(); i++) {

                batch.draw(bala, balasXs.get(i), balasYs.get(i), 40, 40);

                balasRectangles.add(new Rectangle(balasXs.get(i), balasYs.get(i), 40, 40));

                balasXs.set(i, balasXs.get(i) - 1);


            }


            //Creacion rectangulos personaje y enemigo
            manRectangle = new Rectangle(x, 330, 180, 180);
            enemigoRectangle = new Rectangle(1250, 330, 200, 200);

            //Controlamos las colisiones personaje - balas
            for (int i = 0; i < balasRectangles.size(); i++) {

                if (Intersector.overlaps(balasRectangles.get(i), manRectangle)) {
                    vidaPersonaje = (int) (vidaPersonaje - 0.1);
                    Gdx.app.log("Vidas", String.valueOf(vidaPersonaje));

                }
            }

            //Verificamos si el enemigo a caido y eliminamos las balas
            if (vidaEnemigo <= 0) {
                enemigo1Muerto = true;
                batch.draw(enemigo1Caido[EnemigoCaidoFrame], 1250, 330, 200, 200);

                for (int i = 0; i < balasRectangles.size(); i++) {

                    balasXs.remove(i);

                    balasYs.remove(i);

                    balasRectangles.remove(i);

                }
            }

            //Verificamos si la vida del personaje es menor a
            if (vidaPersonaje <= 0) {
                isPersonaje = true;
                finJuego = true;

            }

            //Verificamos la orientacion
            if (derecha) {
                batch.draw(personaje, x, 330, 180, 180);
            } else if (izquierda) {
                batch.draw(personaje2, x, 330, 180, 180);
            }

            //Verificamos si se toca la pantalla
            if (Gdx.input.isTouched()) {
                //Creamos el vector
                Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                //Creamos el rectangulo boton derecho
                Rectangle textureBounds = new Rectangle(500, 900, 180, 180);
                if (textureBounds.contains(tmp.x, tmp.y)) {
                    derecha = true;
                    izquierda = false;

                    //Sumamos 5 a x cada vez que se presione
                    x += 5;

                    //Se pintan por pantalla los elementos
                    batch.draw(imgBackgroung1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    batch.draw(animAgua[aguaFrame1], 0, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 150, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 300, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 450, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 600, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 750, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 900, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1050, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1200, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1350, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1500, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1650, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1800, 10, 150, 180);
                    batch.draw(fDerecha, 500, 50, 180, 180);
                    batch.draw(fIzquierda, 100, 50, 180, 180);
                    batch.draw(fArriba, 300, 50, 180, 180);
                    batch.draw(bEscudo, 1500, 50, 180, 180);
                    batch.draw(bMartillo, 1700, 50, 180, 180);

                    //Pintamos la animacion hacia la derecha
                    batch.draw(animPerson[personFrame], x, 330, 180, 180);

                    if (!enemigo1Muerto) {
                        batch.draw(enemigo1, 1250, 330, 200, 200);
                    }


                    balasRectangles.clear();
                    for (int i = 0; i < balasXs.size(); i++) {

                        batch.draw(bala, balasXs.get(i), balasYs.get(i), 40, 40);

                        balasRectangles.add(new Rectangle(balasXs.get(i), balasYs.get(i), bala.getWidth(), bala.getHeight()));

                        balasXs.set(i, balasXs.get(i) - 1);


                    }


                    for (int i = 0; i < balasRectangles.size(); i++) {

                        if (Intersector.overlaps(balasRectangles.get(i), manRectangle)) {
                            vidaPersonaje = (int) (vidaPersonaje - 0.1);
                            Gdx.app.log("Vidas", String.valueOf(vidaPersonaje));

                        }
                    }

                    if (vidaEnemigo <= 0) {
                        enemigo1Muerto = true;
                        batch.draw(enemigo1Caido[EnemigoCaidoFrame], 1250, 330, 200, 200);
                        for (int i = 0; i < balasRectangles.size(); i++) {

                            balasXs.remove(i);

                            balasYs.remove(i);

                            balasRectangles.remove(i);


                        }
                    }

                    if(vidaPersonaje == 400 || vidaPersonaje > 250){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                        batch.draw(vidaImagen, 350, 950, 120, 120);
                    } else if(vidaPersonaje == 250 || vidaPersonaje > 100){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                    } else if(vidaPersonaje == 100 || vidaPersonaje > 0){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                    }
                }

                //Rectangulo boton Escudo
                Rectangle textureBounds2 = new Rectangle(1450, 900, 180, 180);
                if (textureBounds2.contains(tmp.x, tmp.y)) {
                    batch.draw(imgBackgroung1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    batch.draw(animAgua[aguaFrame1], 0, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 150, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 300, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 450, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 600, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 750, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 900, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1050, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1200, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1350, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1500, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1650, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1800, 10, 150, 180);
                    batch.draw(fDerecha, 500, 50, 180, 180);
                    batch.draw(fIzquierda, 100, 50, 180, 180);
                    batch.draw(fArriba, 300, 50, 180, 180);
                    batch.draw(bEscudo, 1500, 50, 180, 180);
                    batch.draw(bMartillo, 1700, 50, 180, 180);

                    if (!enemigo1Muerto) {
                        batch.draw(enemigo1, 1250, 330, 200, 200);
                    }

                    balasRectangles.clear();
                    for (int i = 0; i < balasXs.size(); i++) {

                        batch.draw(bala, balasXs.get(i), balasYs.get(i), 40, 40);

                        balasRectangles.add(new Rectangle(balasXs.get(i), balasYs.get(i), 40, 40));

                        balasXs.set(i, balasXs.get(i) - 1);


                    }

                    //Deteccion estado escudo . 0 = Personaje con escudo ida, 1 = Personaje sin escudo, 2 = Personaje con escudo vuelta. Tambien controlamos la orientacion
                    if (escudo == 0) {
                        if (derecha) {
                            batch.draw(personajeEscudo, x, 330, 180, 180);
                        } else if (izquierda) {
                            batch.draw(personajeEscudo2, x, 330, 180, 180);
                        }
                    } else if (escudo == 1) {
                        if (derecha) {
                            batch.draw(personaje, x, 330, 180, 180);
                        } else if (izquierda) {
                            batch.draw(personaje2, x, 330, 180, 180);
                        }
                    } else if (escudo == 2) {
                        if (derecha) {
                            batch.draw(personajeEscudo, x, 330, 180, 180);
                        } else if (izquierda) {
                            batch.draw(personajeEscudo2, x, 330, 180, 180);
                        }
                    }

                    manRectangle = new Rectangle(x, 330, 180, 180);
                    enemigoRectangle = new Rectangle(1250, 330, 200, 200);

                    for (int i = 0; i < balasRectangles.size(); i++) {

                        if (Intersector.overlaps(balasRectangles.get(i), manRectangle)) {

                            vidaPersonaje = (int) (vidaPersonaje - 0.1);
                            Gdx.app.log("Vidas", String.valueOf(vidaPersonaje));

                        }
                    }

                    //IF IDA
                    if (ida) {
                        escudo = 1;
                        if (derecha) {
                            xEscudo = xEscudo + 5;
                            //Dibujo la ida del escudo
                            batch.draw(animEscudoIda[EscudoIdaFrame], xEscudo, 330, 80, 130);
                            //Controlamos la colision escudo enemigo
                            escudoRectangle = new Rectangle(xEscudo, 330, 80, 130);
                            if (Intersector.overlaps(escudoRectangle, enemigoRectangle)) {
                                vidaEnemigo = vidaEnemigo - 1;
                                Gdx.app.log("Vidas Enemigo", String.valueOf(vidaEnemigo));

                                if (vidaEnemigo == 0) {
                                    for (int i = 0; i < balasRectangles.size(); i++) {
                                        balasXs.remove(i);

                                        balasYs.remove(i);

                                        balasRectangles.remove(i);
                                    }
                                }

                            }
                        } else if (izquierda) {
                            xEscudo = xEscudo - 5;
                            batch.draw(animEscudoIda[EscudoIdaFrame], xEscudo, 330, 80, 130);
                            //Controlamos la colision escudo enemigo
                            escudoRectangle = new Rectangle(xEscudo, 330, 80, 130);
                            if (Intersector.overlaps(escudoRectangle, enemigoRectangle)) {
                                vidaEnemigo = vidaEnemigo - 1;
                                Gdx.app.log("Vidas Enemigo", String.valueOf(vidaEnemigo));

                                if (vidaEnemigo == 0) {
                                    for (int i = 0; i < balasRectangles.size(); i++) {
                                        balasXs.remove(i);

                                        balasYs.remove(i);

                                        balasRectangles.remove(i);
                                    }
                                }

                            }
                        }
                    }

                    //IF VUELTA
                    if (vuelta) {
                        if (derecha) {
                            xEscudo = xEscudo - 5;
                            batch.draw(animEscudoVuelta[EscudoVueltaFrame], xEscudo, 330, 80, 130);
                        } else if (izquierda) {
                            xEscudo = xEscudo + 5;
                            batch.draw(animEscudoVuelta[EscudoVueltaFrame], xEscudo, 330, 80, 130);
                        }
                    }

                    //Contolamos la ida y la vuelta
                    if (xEscudo == x + 500) {
                        ida = false;
                        vuelta = true;

                    }
                    if (xEscudo == x - 500) {
                        ida = false;
                        vuelta = true;

                    }

                    //Cuando el escudo se encuentre en estas posiciones se cambia de estado
                    if (xEscudo == x + 25) {
                        escudo = 2;
                        ida = true;
                        vuelta = false;

                    }

                    if (xEscudo == x - 25) {
                        escudo = 2;
                        ida = true;
                        vuelta = false;

                    }

                    if (xEscudo == x) {
                        escudo = 0;
                    }

                    if (vidaEnemigo <= 0) {
                        enemigo1Muerto = true;
                        batch.draw(enemigo1Caido[EnemigoCaidoFrame], 1250, 330, 200, 200);
                        for (int i = 0; i < balasRectangles.size(); i++) {

                            balasXs.remove(i);

                            balasYs.remove(i);

                            balasRectangles.remove(i);

                        }
                    }

                    if(vidaPersonaje == 400 || vidaPersonaje > 250){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                        batch.draw(vidaImagen, 350, 950, 120, 120);
                    } else if(vidaPersonaje == 250 || vidaPersonaje > 100){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                    } else if(vidaPersonaje == 100 || vidaPersonaje > 0){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                    }

                }

                //Rectangulo Izquierda
                Rectangle textureBounds3 = new Rectangle(100, 900, 180, 180);
                if (textureBounds3.contains(tmp.x, tmp.y)) {
                    derecha = false;
                    izquierda = true;
                    x -= 5;

                    batch.draw(imgBackgroung1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    batch.draw(animAgua[aguaFrame1], 0, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 150, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 300, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 450, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 600, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 750, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 900, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1050, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1200, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1350, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1500, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1650, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1800, 10, 150, 180);
                    batch.draw(fDerecha, 500, 50, 180, 180);
                    batch.draw(fIzquierda, 100, 50, 180, 180);
                    batch.draw(fArriba, 300, 50, 180, 180);
                    batch.draw(bEscudo, 1500, 50, 180, 180);
                    batch.draw(bMartillo, 1700, 50, 180, 180);

                    batch.draw(animPerson2[personFrame2], x, 330, 180, 180);

                    if (!enemigo1Muerto) {
                        batch.draw(enemigo1, 1250, 330, 200, 200);
                    }

                    balasRectangles.clear();
                    for (int i = 0; i < balasXs.size(); i++) {

                        batch.draw(bala, balasXs.get(i), balasYs.get(i), 40, 40);

                        balasRectangles.add(new Rectangle(balasXs.get(i), balasYs.get(i), 40, 40));

                        balasXs.set(i, balasXs.get(i) - 1);


                    }

                    manRectangle = new Rectangle(x, 330, 180, 180);
                    enemigoRectangle = new Rectangle(1250, 330, 200, 200);

                    for (int i = 0; i < balasRectangles.size(); i++) {

                        if (Intersector.overlaps(balasRectangles.get(i), manRectangle)) {
                            vidaPersonaje = (int) (vidaPersonaje - 0.1);
                            Gdx.app.log("Vidas", String.valueOf(vidaPersonaje));
                        }
                    }

                    if (vidaEnemigo <= 0) {
                        enemigo1Muerto = true;
                        batch.draw(enemigo1Caido[EnemigoCaidoFrame], 1250, 330, 200, 200);
                        for (int i = 0; i < balasRectangles.size(); i++) {

                            balasXs.remove(i);

                            balasYs.remove(i);

                            balasRectangles.remove(i);

                        }
                    }

                    if(vidaPersonaje == 400 || vidaPersonaje > 250){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                        batch.draw(vidaImagen, 350, 950, 120, 120);
                    } else if(vidaPersonaje == 250 || vidaPersonaje > 100){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                    } else if(vidaPersonaje == 100 || vidaPersonaje > 0){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                    }


                }

                //Rectangulo boton salto
                Rectangle textureBounds4 = new Rectangle(300, 900, 180, 180);
                if (textureBounds4.contains(tmp.x, tmp.y)) {
                    batch.draw(imgBackgroung1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    batch.draw(animAgua[aguaFrame1], 0, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 150, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 300, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 450, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 600, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 750, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 900, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1050, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1200, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1350, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1500, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1650, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1800, 10, 150, 180);
                    batch.draw(fDerecha, 500, 50, 180, 180);
                    batch.draw(fIzquierda, 100, 50, 180, 180);
                    batch.draw(fArriba, 300, 50, 180, 180);
                    batch.draw(bEscudo, 1500, 50, 180, 180);
                    batch.draw(bMartillo, 1700, 50, 180, 180);

                    if (!enemigo1Muerto) {
                        batch.draw(enemigo1, 1250, 330, 200, 200);
                    }

                    //Control del salto y segun el valor cambiamos el valor
                    if (ySalto < 500) {
                        ySalto += 5;
                        if (derecha == true) {
                            batch.draw(saltoDerecha, x, ySalto, 180, 180);
                        } else if (izquierda == true) {
                            batch.draw(saltoIzquierda, x, ySalto, 180, 180);
                        }
                    } else if (ySalto == 500) {
                        if (derecha == true) {
                            batch.draw(saltoDerecha, x, ySalto, 180, 180);
                        } else if (izquierda == true) {
                            batch.draw(saltoIzquierda, x, ySalto, 180, 180);
                        }
                    } else if (ySalto > 200) {
                        ySalto -= 5;
                        if (derecha == true) {
                            batch.draw(personaje, x, 330, 180, 180);
                        } else if (izquierda == true) {
                            batch.draw(personaje2, x, 330, 180, 180);
                        }
                    }

                    balasRectangles.clear();
                    for (int i = 0; i < balasXs.size(); i++) {

                        batch.draw(bala, balasXs.get(i), balasYs.get(i), 40, 40);

                        balasRectangles.add(new Rectangle(balasXs.get(i), balasYs.get(i), 40, 40));

                        balasXs.set(i, balasXs.get(i) - 1);


                    }

                    manRectangle = new Rectangle(x, ySalto, 180, 180);
                    enemigoRectangle = new Rectangle(1250, 330, 200, 200);

                    for (int i = 0; i < balasRectangles.size(); i++) {

                        if (Intersector.overlaps(balasRectangles.get(i), manRectangle)) {
                            vidaPersonaje = (int) (vidaPersonaje - 0.1);
                            Gdx.app.log("Vidas", String.valueOf(vidaPersonaje));

                        }
                    }

                    if (vidaEnemigo <= 0) {
                        enemigo1Muerto = true;
                        batch.draw(enemigo1Caido[EnemigoCaidoFrame], 1250, 330, 200, 200);
                        for (int i = 0; i < balasRectangles.size(); i++) {

                            balasXs.remove(i);

                            balasYs.remove(i);

                            balasRectangles.remove(i);

                        }
                    }

                    if(vidaPersonaje == 400 || vidaPersonaje > 250){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                        batch.draw(vidaImagen, 350, 950, 120, 120);
                    } else if(vidaPersonaje == 250 || vidaPersonaje > 100){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                    } else if(vidaPersonaje == 100 || vidaPersonaje > 0){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                    }

                }

                //Rectangulo boton martillo
                Rectangle textureBounds5 = new Rectangle(1700, 900, 180, 180);
                if (textureBounds5.contains(tmp.x, tmp.y)) {
                    batch.draw(imgBackgroung1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    batch.draw(animAgua[aguaFrame1], 0, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 150, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 300, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 450, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 600, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 750, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 900, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1050, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1200, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1350, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame2], 1500, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame3], 1650, 10, 150, 180);
                    batch.draw(animAgua[aguaFrame1], 1800, 10, 150, 180);
                    batch.draw(fDerecha, 500, 50, 180, 180);
                    batch.draw(fIzquierda, 100, 50, 180, 180);
                    batch.draw(fArriba, 300, 50, 180, 180);
                    batch.draw(bEscudo, 1500, 50, 180, 180);
                    batch.draw(bMartillo, 1700, 50, 180, 180);

                    if (!enemigo1Muerto) {
                        batch.draw(enemigo1, 1250, 330, 200, 200);
                    }

                    //Dibujamos el ataque - martillo segun orientacion
                    if (derecha) {
                        batch.draw(martilloDerecha, x, 330, 200, 250);
                        batch.draw(rayos, x + 100, 580, 100, 10000);
                    } else if (izquierda) {
                        batch.draw(martilloIzquierda, x, 330, 200, 250);
                        batch.draw(rayos, x, 580, 100, 10000);
                    }
                    batch.draw(rayos, 1330, 520, 100, 10000);
                    rayosRectangle = new Rectangle(1330, 520, 100, 10000);

                    balasRectangles.clear();
                    for (int i = 0; i < balasXs.size(); i++) {

                        batch.draw(bala, balasXs.get(i), balasYs.get(i), 40, 40);

                        balasRectangles.add(new Rectangle(balasXs.get(i), balasYs.get(i), 40, 40));

                        balasXs.set(i, balasXs.get(i) - 1);


                    }
                    manRectangle = new Rectangle(x, 330, 180, 180);
                    enemigoRectangle = new Rectangle(1250, 330, 200, 200);

                    for (int i = 0; i < balasRectangles.size(); i++) {

                        if (Intersector.overlaps(balasRectangles.get(i), manRectangle)) {
                            vidaPersonaje = (int) (vidaPersonaje - 0.1);
                            Gdx.app.log("Vidas", String.valueOf(vidaPersonaje));

                        }
                    }

                    //Contolamos la colision rayo-enemigo
                    if (Intersector.overlaps(rayosRectangle, enemigoRectangle)) {
                        vidaEnemigo = vidaEnemigo - 1;
                        Gdx.app.log("Vidas Enemigo", String.valueOf(vidaEnemigo));

                        if (vidaEnemigo <= 0) {
                            enemigo1Muerto = true;
                            batch.draw(enemigo1Caido[EnemigoCaidoFrame], 1250, 330, 200, 200);
                            for (int i = 0; i < balasRectangles.size(); i++) {

                                balasXs.remove(i);

                                balasYs.remove(i);

                                balasRectangles.remove(i);

                            }
                        }


                    }

                    if(vidaPersonaje == 400 || vidaPersonaje > 250){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                        batch.draw(vidaImagen, 350, 950, 120, 120);
                    } else if(vidaPersonaje == 250 || vidaPersonaje > 100){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                        batch.draw(vidaImagen, 200, 950, 120, 120);
                    } else if(vidaPersonaje == 100 || vidaPersonaje > 0){
                        batch.draw(vidaImagen, 50, 950, 120, 120);
                    }
                }


            }
        } else {
            //Fin juego
            if(isPersonaje){
                batch.draw(imgBackgroung1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.draw(animAgua[aguaFrame1], 0, 10, 150, 180);
                batch.draw(animAgua[aguaFrame2], 150, 10, 150, 180);
                batch.draw(animAgua[aguaFrame3], 300, 10, 150, 180);
                batch.draw(animAgua[aguaFrame1], 450, 10, 150, 180);
                batch.draw(animAgua[aguaFrame2], 600, 10, 150, 180);
                batch.draw(animAgua[aguaFrame3], 750, 10, 150, 180);
                batch.draw(animAgua[aguaFrame1], 900, 10, 150, 180);
                batch.draw(animAgua[aguaFrame2], 1050, 10, 150, 180);
                batch.draw(animAgua[aguaFrame3], 1200, 10, 150, 180);
                batch.draw(animAgua[aguaFrame1], 1350, 10, 150, 180);
                batch.draw(animAgua[aguaFrame2], 1500, 10, 150, 180);
                batch.draw(animAgua[aguaFrame3], 1650, 10, 150, 180);
                batch.draw(animAgua[aguaFrame1], 1800, 10, 150, 180);
                batch.draw(capiFin, Gdx.graphics.getWidth() / 2 - 100, 330, 180, 180);
            }
        }

        batch.end();


    }

    //Metodo para generar balas
    private void generarBala() {



        float altura = (random.nextFloat() * 230)+225;

        balasYs.add((int)altura);

        balasXs.add(1250);





    }
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
