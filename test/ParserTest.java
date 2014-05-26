import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.otomoringo.manekkodanceparser.Parser;

public class ParserTest {

	private Parser _parser;

	@Before
	public void before() {
		_parser = new Parser();
	}

	// @Test
	// public void parse_xyz() {
	// assertEquals("xyz", _parser.parse("xyz"));
	// }
	//
	// @Test
	// public void parse_3xyz() {
	// assertEquals("xyzxyzxyz", _parser.parse("(3xyz)"));
	// }
	//
	// @Test
	// public void parse_2xy_z() {
	// assertEquals("xyxyz", _parser.parse("(2xy)z"));
	// }
	//
	// @Test
	// public void parse_0xy_z() {
	// assertEquals("z", _parser.parse("(0xy)z"));
	// }
	//
	// @Test
	// public void parse_if_xy_z() {
	// _parser.setBranch(0);
	// assertEquals("xy", _parser.parseIf("[xy|z]"));
	// }
	//
	// @Test
	// public void parse_if_xy_zw() {
	// _parser.setBranch(1);
	// assertEquals("zw", _parser.parseIf("[xy|zw]"));
	// }

	@Test
	public void parse_2_2x_y() {
		_parser.setBranch(0);
		assertEquals("xxyxxy", _parser.parse("(2(2x)y)"));
	}

	@Test
	public void parse_2x_2y_z() {
		_parser.setBranch(0);
		assertEquals("xyyzxyyz", _parser.parse("(2x(2y)z)"));
	}

}
