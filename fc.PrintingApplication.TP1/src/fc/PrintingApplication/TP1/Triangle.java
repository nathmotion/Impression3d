package fc.PrintingApplication.TP1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.owens.oobjloader.builder.FaceVertex;
import com.owens.oobjloader.builder.VertexGeometric;

public class Triangle {
	Vec3f p1;
	Vec3f p2;
	Vec3f p3;
	
	ArrayList<Vec3f> sommet=new ArrayList<Vec3f>();
	

	
	BufferedImage intersectionTrancheSegment(BufferedImage tranche,Float pTranche ,ArrayList<FaceVertex> pointTranche){
		pointTranche=new ArrayList<FaceVertex>();
		

		float x=0;
		float y=0;
		float z=0;
		
		/**float zmax= Math.max(f.vertices.get(0).v.z,f.vertices.get(1).v.z);
		zmax=Math.max(zmax,f.vertices.get(1).v.z);
		
		float zmin= Math.min(f.vertices.get(0).v.z,f.vertices.get(1).v.z);
		zmin=Math.min(zmin,f.vertices.get(1).v.z);
		**/
		float zmax=0;
		float zmin=0;
		int indMax=0;
		int indMin=0;
		for(int i=0;i<3;i++){
			if( i==2){
				
			// detection de zmin et zmax
				zmax= Math.max(sommet.get(2).z,sommet.get(0).z);
				 zmin= Math.min(sommet.get(2).z,sommet.get(0).z);
			// on regarde le sens du edges vecteur plus point smin au zmax
				 if(zmax ==sommet.get(0).z){
					 indMax=0;
					 indMin=2;
				 }
				 else{
					 indMax=2;
					 indMin=0;
				 }
				 
				
			}
			else{
			 zmax= Math.max(sommet.get(i).z,sommet.get(i+1).z);
			 zmin= Math.min(sommet.get(i).z,sommet.get(i+1).z);
			 if(zmax == sommet.get(i).z){
				 indMax=i;
				 indMin=i+1;
			 }
			 else{
				 indMax=i+1;
				 indMin=i;
			 }
			 
		
			}

			 if((pTranche>zmin && pTranche<zmax) )
			 {
		
			
				float t=(pTranche-sommet.get(indMin).z)/(sommet.get(indMax).z-sommet.get(indMin).z);	
				
				if( t<=1 && t>=0){
				x=sommet.get(indMin).x+(sommet.get(indMax).x-sommet.get(indMin).x)*t;
				y=sommet.get(indMin).y+(sommet.get(indMax).y-sommet.get(indMin).y)*t;	
				FaceVertex vertex=new FaceVertex();
				vertex.v=new VertexGeometric(x,y,pTranche);
				pointTranche.add(vertex);
				tranche.setRGB((int)((x+40)*10),(int)((y+40)*10), Color.RED.getRGB());
				}		
	/*+		 Segment2f s1=new Segment2f();			 
			 s1.p1=pointTranche.get(0).v;
			 s1.p2=pointTranche.get(1).v;
			listeST.add(s1);*/
			 }
			

	}
		if( pointTranche.size()==2){
			
			//	bresenhamLine(tranche,(pointTranche.get(1).v.x+40)*10,(pointTranche.get(0).v.x+40)*10,(pointTranche.get(1).v.y+40)*10,(pointTranche.get(0).v.y+40)*10);
				bresenhamLine(tranche,(pointTranche.get(0).v.x+40)*10,(pointTranche.get(1).v.x+40)*10,(pointTranche.get(0).v.y+40)*10,(pointTranche.get(1).v.y+40)*10);
			//g.drawLine((int)(pointTranche.get(0).v.x+40)*10,(int)(pointTranche.get(1).v.x+40)*10,(int)(pointTranche.get(0).v.y+40)*10,(int)(pointTranche.get(1).v.y+40)*10);
			}

		return tranche;
	}
	
	
	
	
	
	/***
	 * 				 TRACAGE LIGNE BRESENHAM 
	 * **/
	

	static BufferedImage bresenhamLine(BufferedImage im,float x1,float x2,float y1, float y2){
		int x=(int)x1;
		int y=(int)y1;
		int dx= (int)(x2-x1);
		int dy=(int)(y2-y1);
		int xinc=(dx>0)?1:-1;
		int yinc=(dy>0)?1:-1;
		dx=Math.abs(dx);
		dy=Math.abs(dy);
		im.setRGB((int)x1,(int)y1, Color.GREEN.getRGB());
		im.setRGB((int)x2,(int)y2, Color.GREEN.getRGB());
		im.setRGB(x,y, Color.GREEN.getRGB());
		if(dx>dy){
		 int cumul=dx/2;
		 for(int i=1;i<=dx;i++){
			 x+=xinc;
			 cumul+=dy;
			 if(cumul>=dx){
				cumul-=dx;
				y+=yinc;
			 }

		  		im.setRGB(x,y, Color.GREEN.getRGB()); 

		 }
		}
		else{
			int cumul = dy / 2 ;

			    for (int i = 1 ; i <= dy ; i++ ) {
			      y += yinc ;
			      cumul += dx ;
			      if ( cumul >= dy ) {
			        cumul -= dy ;
			        x += xinc ; }
			 
			  		im.setRGB(x,y, Color.GREEN.getRGB()); 
			  	}
			   }		
				
				
		
		
		
		return im;
		
	}
}




