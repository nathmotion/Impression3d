package fc.PrintingApplication.TP1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import com.owens.oobjloader.builder.Build;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import com.owens.oobjloader.builder.VertexGeometric;
import com.owens.oobjloader.parser.Parse;
import java.awt.Graphics2D;
public class Main
{
	static Graphics2D g;
	static BufferedImage im = null;
	static BufferedImage tranche = null;
	static int sizeX=800;
	static int sizeY=800;
	public static ArrayList<Segment2f> listeST= new ArrayList<Segment2f>();
	static float pasTranche = (float)0.2;// plus tard quand on voudra afficher plusieur tranche
	//static float pTranche=(float) 2.0;//positionde la tranche
	public static ArrayList<FaceVertex> pointTranche=new ArrayList<FaceVertex>(); // toutes les triangles de l'objet qui seront intersecter .
	public static ArrayList<FaceVertex> Sommetobjet=new ArrayList<FaceVertex>(); //tout les sommet de lobjet charge
	static ArrayList<Face> FaceObjet=new ArrayList<Face>();// tout les triangle de l'objet charge
	
	//
	// Dans ce TP, lire et parser un fichier OBJ
	// puis realiser des coupes par plan Z.
	// Une fois les perimetres extraits:
	// - effectuer une triangulation de Delaunay contrainte.
	// - rasteriser dans une bitmap (remplissage de polygone arbitraire) (si le temps le permet)
	//
	
	
	
	public static void main(String[] argv)
	{
		float boiteEnglobantX[]=new float[2] ;
		float boiteEnglobantY[]=new float[2] ;
		float boiteEnglobantZ[]=new float[2] ;
		String name="CuteOcto.obj";
		im= new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_INT_RGB);
		tranche= new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<sizeX;x++){
			for(int y=0;y<sizeY;y++){

						im.setRGB(x, y, Color.BLACK.getRGB());
			}
		}
		
	// on recupere les traingle  > face ?
		// on faier un boite englobante  on agrandie un peu dans le cas d'un cube 
		// on parcour l'axe des y (ou Z) et on ignore les faces/triangles qui sont pas intersecter par la tranche 
		// on doit calcul les point des des intersection avec la tranche ...puis on es stocker dans un tableau qu'on trace sur un bitmp
		parseObjFile(name);
		// on recupere des boites englobant
		try {
			ImageIO.write(im, "png", new File("./Test.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		float min=5000;
		float max=-5000;
		float minY=5000;
		float maxY=-5000;
		float minZ=10;
		float maxZ=0;
		
		for(FaceVertex vertex : Sommetobjet ){
				minZ=Math.min(minZ,vertex.v.z);
				maxZ=Math.max(maxZ, vertex.v.z);
		}
		
		
	
		//for(float p=minZ;p<maxZ;p=(float) (p+0.5));
		// on parcours les face triee et on calcul les point d'intersection pour chaque segement
		// equation
		float pTranche=minZ;
		int j =0;
		System.out.println("zmax"+maxZ);
		System.out.println("zmin"+minZ);
		while(pTranche<maxZ){
			
			tranche= new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_INT_RGB);
			g=tranche.createGraphics();
			//g.setBackground(Color.BLACK);
			g.setColor(Color.GREEN);
			
			for(Face f:FaceObjet){
				Triangle t= new Triangle();
				
				for (int i= 0;i)
				t.sommet()
	
		
	

			// trie le tabbleau des point ?
			}
			pTranche=(float) (pTranche+0.20);
			listeST =new ArrayList<Segment2f>();
			try {
				j++;
				ImageIO.write(tranche, "bmp", new File("./tranche"+j+".bmp"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
		
			
		}
}// end main 
	
	public static void parseObjFile(String filename)
	{
		im= new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_INT_RGB);

	    try
	    {
	        Build builder = new Build();
	        Parse obj = new Parse(builder, new File(filename).toURI().toURL());
	        
	        // Enumeration des sommets
	        
 	        for (FaceVertex vertex : builder.faceVerticeList)
	        {
	        	float x = vertex.v.x;
		    	float y = vertex.v.y;
	        	float z = vertex.v.z;
	        	//...
	        	//im.setRGB((int)((x+50)*8),(int)((y+50)*8), Color.WHITE.getRGB());
	        	
	        	Sommetobjet.add(vertex);
	        }

	        // Enumeration des faces (souvent des triangles, mais peuvent comporter plus de sommets dans certains cas)
	        
	        for (Face face : builder.faces)
	        {
	        	// Parcours des triangles de cette face
	        	for (int i=1; i <= (face.vertices.size() - 2); i++)
	        	{
	        		
	        		int vertexIndex1 = face.vertices.get(0).index;
	        		int vertexIndex2 = face.vertices.get(i).index;
	        		int vertexIndex3 = face.vertices.get(i+1).index;
	        		
	        		FaceVertex vertex1 = builder.faceVerticeList.get(vertexIndex1);
	        		FaceVertex vertex2 = builder.faceVerticeList.get(vertexIndex2);
	        		FaceVertex vertex3 = builder.faceVerticeList.get(vertexIndex3);
	        		
	        		// ...
	        		FaceObjet.add(face);
	        	}
	        }
	    }
	    catch (java.io.FileNotFoundException e)
	    {
	    	System.out.println("FileNotFoundException loading file "+filename+", e=" + e);
	        e.printStackTrace();
	    }
	    catch (java.io.IOException e)
	    {
	    	System.out.println("IOException loading file "+filename+", e=" + e);
	        e.printStackTrace();
	    }
	}
	
	
	
		
		
		
		
		/*ArrayList<FaceVertex> faceproche=l;

		 * for( int i=0 ;i<l.size()-2;i=i+2){
			int dist=5000;
			
				for( int j=i+2;j<faceproche.size()-1;j++){
					int tmp =(int) Math.sqrt(Math.pow((faceproche.get(j).v.x-l.get(i).v.x),2)+Math.pow((faceproche.get(j).v.y-l.get(i).v.y),2));
					if(dist>tmp){
						dist=tmp;
						index=j;
					}
					
				}
				bresenhamLine(tranche,(l.get(i).v.x+40)*10,(l.get(index).v.x+40)*10,(l.get(i).v.y+40)*10,(l.get(index).v.y+40)*10);
				faceproche.remove(index);
				dist=5000;
				//index=0;
				for( int j=i+2;j<faceproche.size()-1;j++){
					int tmp =(int) Math.sqrt(Math.pow((faceproche.get(j).v.x-l.get(i+1).v.x),2)+Math.pow((faceproche.get(j).v.y-l.get(i+1).v.y),2));
					if(dist>tmp){
						dist=tmp;
						index=j;
					}
					
				}
				
				bresenhamLine(tranche,(l.get(i+1).v.x+40)*10,(l.get(index).v.x+40)*10,(l.get(i+1).v.y+40)*10,(l.get(index).v.y+40)*10);
				faceproche.remove(index);
				dist=5000;*/
			
		
		
		
		
		
		
	

}
