package com.biblioteca.digital.service;

import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class QueueService {

        private Queue<Object> fila = new ConcurrentLinkedQueue<>();


        public void adicionarNaFila(Object objeto){
            fila.add(objeto);

        }
        public Object retirarDaFila(){

            return fila.poll(); //retorna e remove o primeiro objeto seguindo a FIFO
        }
        public Object verDoTopo(){
            return fila.peek(); // visualiza o primeiro objeto da fila sem removê-lo
        }

        public int tamanho(){
            return fila.size();
        }

        public boolean estaVazia(){
            return fila.isEmpty();
        }


}
