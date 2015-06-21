package ca.hyperreal

import collection.mutable.ListBuffer

import math._


package object nvector
{
	val equatorialRadius = 6378.1370
	val polarRadius = 6356.7523
	
	// Mean radius as defined by the International Union of Geodesy and Geophysics (IUGG)
	val R1 = (2*equatorialRadius + polarRadius)/3
	
	type Angle = Double
	type Distance = Double
	type NVector = Vector[Double]
	
	val NorthVector: NVector = Vector( 0, 0, 1 )
	
	val North = 0
	val NorthEast = 45
	val East = 90
	val SouthEast = 135
	val South = 180
	val SouthWest = -135
	val West = -90
	val NorthWest = -45
	
	implicit def _raiseVector( x: NVector ) =
		new AnyRef
		{
			def +( y: NVector ) = x zip y map {case (a, b) => a + b}
			def *( s: Double ) = x map (s*_)
			def /( s: Double ) = x map (_/s)
			def dot( y: NVector ) = x zip y map {case (a, b) => a*b} sum
			def cross( y: NVector ) = Vector( x(1)*y(2) - x(2)*y(1), x(2)*y(0) - x(0)*y(2), x(0)*y(1) - x(1)*y(0) )
		}
	
	case class Geo( latitude: Double, longitude: Double )
	{
		require( latitude >= -90 && latitude <= 90, "-90 <= latitude <= 90" )
		require( longitude >= -180 && longitude <= 180, "-180 <= longitude <= 180" )
	}

	def norm( n: NVector ) = sqrt( n map (a => a*a) sum )

	def normalize( n: NVector ) = n/norm( n )
	
	def rotate( v: NVector, k: NVector, t: Double ) =
	{
	val ct = cos( t )
	
		v*ct + (k cross v)*sin(t) + k*(k dot v)*(1 - ct)
	}
	
	def fromGeo( loc: Geo ) =
	{
	val phi = toRadians( loc.latitude )
	val lambda = toRadians( loc.longitude )
	val cosphi = cos( phi )
	
		Vector( cosphi*cos(lambda), cosphi*sin(lambda), sin(phi) )
	}
	
	def toGeo( n: NVector ) = Geo( toDegrees(atan2(n(2), sqrt(n(0)*n(0) + n(1)*n(1)))), toDegrees(atan2(n(1), n(0))) )
	
	def centralAngle( a: NVector, b: NVector ): Angle = atan2( norm(a cross b), a dot b )
	
	def centralAngle( a: Geo, b: Geo ): Angle = centralAngle( fromGeo(a), fromGeo(b) )
	
	def distance( a: NVector, b: NVector ): Distance = distance( centralAngle(a, b) )
	
	def distance( a: Geo, b: Geo ): Distance = distance( centralAngle(a, b) )
	
	def distance( a: Angle ): Distance = a*R1
	
	def kilometers( dist: Double ) = dist/R1
	
	def degrees( bearing: Double ) = (-bearing).toRadians
	
	def distant( start: NVector, bearing: Double, distance: Double ): NVector =
		rotate( start, rotate(normalize(start cross NorthVector), start, degrees(bearing)), kilometers(distance) )
	
	def distant( start: Geo, bearing: Double, distance: Double ): Geo = toGeo( distant(fromGeo(start), bearing, distance) )
}