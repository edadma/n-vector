package ca.hyperreal.nvector

import org.scalatest._
import prop.PropertyChecks


class Tests extends FreeSpec with PropertyChecks with Matchers
{
	"distance" in
	{
	val montreal = Geo(45.5017, -73.5673)
	val m = fromGeo( montreal )
	val axis = normalize( m cross NorthVector )
	val d100 = kilometers( 100 )
	val n100 = rotate( m, axis, d100 )
	
		distance( m, n100 ) shouldBe 100.0 +- .00001
	}
	
	"bearing" in
	{
	val montreal = Geo(45.5017, -73.5673)
	val m = fromGeo( montreal )
	val axis = normalize( m cross NorthVector )
	val b90 = degrees( 90 )
	val raxis = rotate( axis, m, b90 )
	val d100 = kilometers( 100 )
	val d100b90 = rotate( m, raxis, d100 )
	
		distance( m, d100b90 ) shouldBe 100.0 +- .00001
	}
	
	"distant" in
	{
	val montreal = Geo(45.5017, -73.5673)
	val east100km = distant( montreal, East, 100 )
	
		distance( montreal, east100km ) shouldBe 100.0 +- .00001
	}
}