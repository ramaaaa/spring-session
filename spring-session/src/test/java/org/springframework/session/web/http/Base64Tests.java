/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.session.web.http;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Base64}.
 * @author Luke Taylor
 * @author Vedran Pavic
 */
public class Base64Tests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void isBase64ReturnsTrueForValidBase64() {
		assertThat(Base64.isBase64(new byte[] { (byte) 'A', (byte) 'B', (byte) 'C',
				(byte) 'D' })).isTrue();
	}

	@Test
	public void isBase64ReturnsFalseForInvalidBase64() throws Exception {
		// Include invalid '`' character
		assertThat(Base64.isBase64(new byte[] { (byte) 'A', (byte) 'B', (byte) 'C',
				(byte) '`' })).isFalse();
	}

	@Test
	public void isBase64RejectsNull() {
		this.thrown.expect(NullPointerException.class);
		Base64.isBase64(null);
	}

	@Test
	public void isBase64RejectsInvalidLength() {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Base64-encoded string must have at least four " +
				"characters, but length specified was 1");
		Base64.isBase64(new byte[] { (byte) 'A' });
	}

}
