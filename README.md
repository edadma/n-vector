n-vector
=======

**n-vector** is a package for performing geographical distance calculations using the so called *n-vector* geographic coordinate system.

Here is an example that computes a distant point 100 kilometers north-west of Montreal (Canada).

	import ca.hyperreal.nvector

	object Main extends App
	{
		val montreal = Geo(45.5017, -73.5673)
		val nw100km = distant( montreal, NorthWest, 100 )
		
		println( montreal )
		println( nw100km )
		println( distance(montreal, nw100km) formatted "%.2f" )
	}

The above code prints

	Geo(45.5017,-73.5673)
	Geo(46.133970000952324,-74.48496234819456)
	100.00
