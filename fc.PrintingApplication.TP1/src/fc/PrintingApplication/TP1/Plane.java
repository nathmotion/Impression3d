// Copyright (c) 2016,2017 Fr�d�ric Claux, Universit� de Limoges. Tous droits r�serv�s.

package fc.PrintingApplication.TP1;

public class Plane
{
	public Vec3f m_Point;
	public Vec3f m_Normal;
	
	public Plane(Vec3f point, Vec3f normal)
	{
		m_Point = point;
		m_Normal = normal;
    }
	
	public float distanceToPoint(Vec3f p)
	{
		return 0;
    }
}
