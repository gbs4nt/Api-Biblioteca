package com.biblioteca.digital.service;

import com.biblioteca.digital.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class QueueService {

        private Queue<Usuario> fila = new ConcurrentLinkedQueue<>();


        public void adicionarNaFila(Usuario objeto){
            fila.add(objeto);

        }
        public Usuario retirarDaFila(){

            return fila.poll(); //retorna e remove o primeiro objeto seguindo a FIFO
        }
        public Usuario verDoTopo(){
            return fila.peek(); // visualiza o primeiro objeto da fila sem removê-lo
        }


    public int tamanho(){
            return fila.size();
        }

        public boolean estaVazia(){
            return fila.isEmpty();
        }

    public Queue<Usuario> getFila() {
        return fila;
    }

    public void setFila(Queue<Usuario> fila) {
        this.fila = fila;
    }


}
