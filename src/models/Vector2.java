package models;

public class Vector2 {
	public  double x;
	public  double y;
	
	public Vector2(Double _x, Double _y)
	{
		x = _x;
		y = _y;
	}
	
	public Vector2 normailized() 
	{
		double ls = x * x + y * y;
		double invNorm = 1.0f / (double)Math.sqrt(ls);

        return new Vector2(
            x * invNorm,
            y * invNorm);
	}
	
	public static double Distance(Double x1, Double y1, Double x2, Double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public static double Distance(Vector2 vec1, Vector2 vec2)
	{
		return Math.sqrt((vec1.x-vec2.x)*(vec1.x-vec2.x) + (vec1.y-vec2.y)*(vec1.y-vec2.y));
	}
	
	public static Vector2 getDirection(Vector2 start, Vector2 end)
	{
		return new Vector2(end.x-start.x, end.y - start.y);
	}
	
	public static Vector2 multiply(Vector2 vec, Double number)
	{
		return new Vector2(vec.x * number, vec.y*number);
	}
	
	public void add(Vector2 vec)
	{
		x +=vec.x;
		y +=vec.y;
	}
}
