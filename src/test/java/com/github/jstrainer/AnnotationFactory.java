package com.github.jstrainer;

import java.lang.annotation.Annotation;

import com.github.jstrainer.filter.Alpha;
import com.github.jstrainer.filter.Alphanum;
import com.github.jstrainer.filter.Blacklist;
import com.github.jstrainer.filter.Numeric;
import com.github.jstrainer.filter.LeftPad;
import com.github.jstrainer.filter.RightPad;
import com.github.jstrainer.filter.Prefix;
import com.github.jstrainer.filter.Replace;
import com.github.jstrainer.filter.Round;
import com.github.jstrainer.filter.RoundDown;
import com.github.jstrainer.filter.RoundUp;
import com.github.jstrainer.filter.StripNewlines;
import com.github.jstrainer.filter.StripTags;
import com.github.jstrainer.filter.Suffix;
import com.github.jstrainer.filter.ToLowerCase;
import com.github.jstrainer.filter.ToUpperCase;
import com.github.jstrainer.filter.Trim;
import com.github.jstrainer.filter.Whitelist;

public class AnnotationFactory {

	public static LeftPad getPadLeft(int length, char padChar) {
		return new LeftPad() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return LeftPad.class;
			}

			@Override
			public int length() {
				return length;
			}

			@Override
			public char padChar() {
				return padChar;
			}

		};
	}

	public static RightPad getRightPad(int length, char padChar) {
		return new RightPad() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return RightPad.class;
			}

			@Override
			public int length() {
				return length;
			}

			@Override
			public char padChar() {
				return padChar;
			}
		};
	}

	public static Alpha getAlpha(boolean allowSpace, boolean allowAccents) {
		return new Alpha() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Alpha.class;
			}

			@Override
			public boolean allowSpace() {
				return allowSpace;
			}

			@Override
			public boolean allowAccents() {
				return allowAccents;
			}
		};
	}

	public static Alphanum getAlphanum(boolean allowSpace) {
		return new Alphanum() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Alphanum.class;
			}

			@Override
			public boolean allowSpace() {
				return allowSpace;
			}

			@Override
			public boolean allowOtherCharSet() {
				return false;
			}

			@Override
			public boolean allowNewline() {
				return false;
			}
		};
	}

	public static Blacklist getBlacklist(String[] list) {
		return new Blacklist() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Blacklist.class;
			}

			@Override
			public String[] list() {
				return list;
			}
		};
	}

	public static Numeric getNumeric() {
		return new Numeric() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Numeric.class;
			}
		};
	}

	public static StripNewlines getStripNewlines() {
		return new StripNewlines() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return StripNewlines.class;
			}
		};
	}

	public static StripTags getStripTags() {
		return new StripTags() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return StripTags.class;
			}
		};
	}

	public static Suffix getSuffix(String value) {
		return new Suffix() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Suffix.class;
			}

			@Override
			public String value() {
				return "_suffix";
			}
		};
	}

	public static Prefix getPrefix(String value) {
		return new Prefix() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Prefix.class;
			}

			@Override
			public String value() {
				return "prefix_";
			}
		};
	}

	public static ToLowerCase getToLower() {
		return new ToLowerCase() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ToLowerCase.class;
			}
		};
	}

	public static ToUpperCase getToUpper() {
		return new ToUpperCase() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ToUpperCase.class;
			}
		};
	}

	public static Trim getTrim(String stripChar) {
		return new Trim() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Trim.class;
			}

			@Override
			public String stripChar() {
				return stripChar;
			}
		};
	}

	public static Trim getTrim() {
		return getTrim(Trim.DEFAULT);
	}

	public static Whitelist getWhitelist(String[] list) {
		return new Whitelist() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Whitelist.class;
			}

			@Override
			public String[] list() {
				return list;
			}
		};
	}

	public static RoundUp getRoundUp(int scale) {
		return new RoundUp() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return RoundUp.class;
			}

			@Override
			public int scale() {
				return scale;
			}
		};
	}

	public static RoundDown getRoundDown(int scale) {
		return new RoundDown() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return RoundDown.class;
			}

			@Override
			public int scale() {
				return scale;
			}
		};
	}

	public static Round getRound(int scale) {
		return new Round() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Round.class;
			}

			@Override
			public int scale() {
				return scale;
			}
		};
	}

	public static Replace getReplace(String search, String replacement, boolean ignoreCase, boolean all) {
		return new Replace() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Replace.class;
			}

			@Override
			public String search() {
				return search;
			}

			@Override
			public String replacement() {
				return replacement;
			}

			@Override
			public boolean ignoreCase() {
				return ignoreCase;
			}

			@Override
			public boolean all() {
				return all;
			}
		};
	}
}
