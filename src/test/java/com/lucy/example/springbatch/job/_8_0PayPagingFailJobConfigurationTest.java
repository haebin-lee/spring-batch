package com.lucy.example.springbatch.job;

import com.lucy.example.springbatch.config.TestBatchConfig;
import com.lucy.example.springbatch.entity.Pay2;
import com.lucy.example.springbatch.repository.Pay2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBatchTest
@SpringBootTest(classes = {_8_0PayPagingFailJobConfiguration.class, TestBatchConfig.class})
@TestPropertySource(properties = {"job.name=" + _8_0PayPagingFailJobConfiguration.JOB_NAME})
public class _8_0PayPagingFailJobConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private Pay2Repository pay2Repository;

    @Test
    public void 같은조건을읽고_업데이트할때() throws Exception {
        //given
        for (long i = 0; i < 50; i++) {
            pay2Repository.save(new Pay2(i, false));
        }
        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        //then
        assertThat(jobExecution.getStatus(), is(BatchStatus.COMPLETED));
        assertThat(pay2Repository.findAllBySuccessStatusIsTrue().size(), is(50));

    }
}