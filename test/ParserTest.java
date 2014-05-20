import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	private Parser _parser;

	@Before
	public void before() {
		_parser = new Parser();
	}

	@Test
	public void parse_xyz() {
		assertEquals("xyz", _parser.parse("xyz"));
	}

	@Test
	public void parse_if_xy_z() {
		_parser.setBranch(1);
		assertEquals("xy", _parser.parseIf("[xy|z]"));
	}

}
