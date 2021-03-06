///*
// * Clase que permite que los servicios puedan ser utilizados desde JS. * 
// * 
// */
//
//package com.agenda.cors;
//import com.sun.jersey.spi.container.ContainerRequest;
//import com.sun.jersey.spi.container.ContainerResponse;
//import com.sun.jersey.spi.container.ContainerResponseFilter;
//
//public class CORSFilter implements ContainerResponseFilter{
//	@Override
//    public ContainerResponse filter(ContainerRequest creq, ContainerResponse cresp) {
//
//        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
//        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Credentials", "true");
//        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
//        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type,Content-Length, Accept, X-Requested-With, Origin, Authorization");
//        
//        return cresp;
//    }
//}
