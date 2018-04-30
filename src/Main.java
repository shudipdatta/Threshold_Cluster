import java.util.ArrayList;

public class Main {
	
	public class Cluster {
		//public double centroid;
		public double[] edges;
		public ArrayList<Double> members;
		
		public Cluster() {
			//centroid = 0;
			edges = new double[2];
			members = new ArrayList<Double>();
		}
	}
	
	ArrayList<Cluster> clusters = new ArrayList<Cluster>();
	/*
	public void DoClusteringBackup2(double[] elements, int threshold) {
		for(int i=0; i<elements.length; i++) {
			boolean makeNewCluster = true;
			Cluster selectedC = null;
			double minDistance = 10000000;
			double element = elements[i];
			
			for(Cluster c: clusters) {
				double distance = Math.abs(c.centroid-element);
				if (distance > 180) distance = 360-distance;
				
				if (distance <= threshold/2) {
					if(distance < minDistance) {
						minDistance = distance;
						selectedC = c;
					}
				}
			}
			if (selectedC != null) {
				makeNewCluster = false;
				selectedC.members.add(element);
				double currentDist = Math.abs(selectedC.edges[0]-selectedC.edges[1]);
				if(currentDist > 180) currentDist = 360 - currentDist;
				double edge0toDist = Math.abs(selectedC.edges[0]-element);
				if(edge0toDist > 180) edge0toDist = 360 - edge0toDist;
				double edge1toDist = Math.abs(selectedC.edges[1]-element);
				if(edge1toDist > 180) edge1toDist = 360 - edge1toDist;
				
				if(edge0toDist + edge1toDist > currentDist) {
					if(edge0toDist < edge1toDist) selectedC.edges[0] = element;
					else selectedC.edges[1] = element;
				}	
				
				//selectedC.centroid = Math.abs(selectedC.edges[0]-selectedC.edges[1])/2; 
				//double edge0toCentroid = selectedC.edges[0] + selectedC.centroid;
				//if(edge0toCentroid > 180) edge0toCentroid = 360-edge0toCentroid;
				//double edge1toCentroid = selectedC.edges[1] + selectedC.centroid;
				//if(edge1toCentroid > 180) edge1toCentroid = 360-edge1toCentroid;
				
				
				//selectedC.centroid = Math.atan2((Math.sin(selectedC.edges[0])+Math.sin(selectedC.edges[1]))/2, 
				//								(Math.cos(selectedC.edges[0])+Math.cos(selectedC.edges[1]))/2 )
				//					* 180 / Math.PI;
				
				selectedC.centroid = ((selectedC.edges[0]+selectedC.edges[1])/2) % 360;
			}
			if(makeNewCluster == true) {
				Cluster c = new Cluster();
				c.centroid = element;
				c.edges[0] = element;
				c.edges[1] = element;
				c.members.add(element);
				clusters.add(c);
			}
		}
	}
	
	public void DoClusteringBackup1(double[] elements, int threshold) {
		for(int i=0; i<elements.length; i++) {
			boolean makeNewCluster = true;
			Cluster selectedC = null;
			double minDistance = 10000000;
			double element = elements[i];
			
			for(Cluster c: clusters) {
				double distance = Math.abs(c.centroid-element);
				if (distance > 180) distance = 360-distance;
				
				if (distance < threshold) {
					if(distance < minDistance) {
						minDistance = distance;
						selectedC = c;
					}
				}
			}
			if (selectedC != null) {
				makeNewCluster = false;
				selectedC.members.add(element);
				double currentDist = Math.abs(selectedC.edges[0]-selectedC.edges[1]);
				if(currentDist > 180) currentDist = 360 - currentDist;
				double edge0toDist = Math.abs(selectedC.edges[0]-element);
				if(edge0toDist > 180) edge0toDist = 360 - edge0toDist;
				double edge1toDist = Math.abs(selectedC.edges[1]-element);
				if(edge1toDist > 180) edge1toDist = 360 - edge1toDist;
				
				if(edge0toDist + edge1toDist > currentDist) {
					if(edge0toDist < edge1toDist) selectedC.edges[0] = element;
					else selectedC.edges[1] = element;
				}				
				selectedC.centroid = currentDist/2; 
			}
			if(makeNewCluster == true) {
				Cluster c = new Cluster();
				c.centroid = element;
				c.edges[0] = element;
				c.edges[1] = element;
				c.members.add(element);
				clusters.add(c);
			}
		}
	}
	*/
	
	public void DoClustering(double[] elements, int threshold) {
		for(int i=0; i<elements.length; i++) {
			boolean makeNewCluster = true;
			Cluster selectedC = null;
			double minDistance = 10000000;
			double element = elements[i];
			
			for(Cluster c: clusters) {
				double dist0 = Math.abs(c.edges[0]-element);
				if (dist0 > 180) dist0 = 360-dist0;
				
				double dist1 = Math.abs(c.edges[1]-element);
				if (dist1 > 180) dist1 = 360-dist1;
				
				if( (dist0 <= threshold/2 && dist1 <= threshold)
				||  (dist0 <= threshold && dist1 <= threshold/2)
				||  (dist0 == dist1))
				{				
					if(dist0+dist1 < minDistance) {
						minDistance = dist0+dist1;
						selectedC = c;
					}
				}
			}
			if (selectedC != null) {
				makeNewCluster = false;
				selectedC.members.add(element);
				double currentDist = Math.abs(selectedC.edges[0]-selectedC.edges[1]);
				if(currentDist > 180) currentDist = 360 - currentDist;
				double edge0toDist = Math.abs(selectedC.edges[0]-element);
				if(edge0toDist > 180) edge0toDist = 360 - edge0toDist;
				double edge1toDist = Math.abs(selectedC.edges[1]-element);
				if(edge1toDist > 180) edge1toDist = 360 - edge1toDist;
				
				if(edge0toDist + edge1toDist > currentDist) {
					if(edge0toDist < edge1toDist) selectedC.edges[0] = element;
					else selectedC.edges[1] = element;
				}				
			}
			if(makeNewCluster == true) {
				Cluster c = new Cluster();
				c.edges[0] = element;
				c.edges[1] = element;
				c.members.add(element);
				clusters.add(c);
			}
		}
	}
	
	
	public void PrintCluster() {
		for(Cluster c: clusters) {
			System.out.println("Edges:[" + (int)c.edges[0] + "," + (int)c.edges[1] + "]");
			System.out.print("Members: ");
			for(double m: c.members) {
				System.out.print((int)m + "\t");
			}
			System.out.print("\n\n");
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		double[] elements = {0, 45, 180, 135, 315, 320, 240, 340, 100};
		m.DoClustering(elements, 90);
		m.PrintCluster();
	}

}
