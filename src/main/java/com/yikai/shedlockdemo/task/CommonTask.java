package com.yikai.shedlockdemo.task;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description
 * 请注意，ShedLock不是，也永远不会是成熟的调度程序，它只是一个锁。如果您需要分布式调度程序，请使用另一个项目。
 * ShedLock设计用于在您计划好的任务尚未准备好并行执行但可以安全地重复执行的情况下使用。此外，锁是基于时间的，
 * ShedLock假定节点上的时钟已同步。
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/27 10:46
 */
@EnableScheduling
@Slf4j
@Component
public class CommonTask {


    /**
     * 您还可以设置lockAtMostFor属性，该属性指定在执行节点死亡时应将锁保留多长时间。这只是一个后备，在正常情况下，
     * 任务完成后立即释放锁定。 您必须将其设置lockAtMostFor为比正常执行时间长得多的值。
     * 如果任务花费的时间长于 lockAtMostFor所导致的行为可能是不可预测的（多于一个进程将有效地持有该锁）。
     *
     * 如果你不指定lockAtMostFor在@SchedulerLock默认值从@EnableSchedulerLock将被使用。
     *
     * 最后，您可以设置lockAtLeastFor属性，该属性指定应保留锁定的最短时间。其主要目的是在任务很短且节点之间的时钟差的情况下，
     * 防止从多个节点执行。
     *
     * 执行方法所需要的的时间 远远< lockAtMostFor < cron间隔           服务器之间的时间差<lockAtLeastFor
     * @throws InterruptedException
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    //这三个参数必须设置
    @SchedulerLock(name = "sayHello",lockAtMostFor = "PT20S",lockAtLeastFor = "PT2S")
    public void say() throws InterruptedException {
        log.info("hello");
    }


}
