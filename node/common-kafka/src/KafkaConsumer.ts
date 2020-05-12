import { Consumer, EachMessagePayload, ConsumerSubscribeTopic } from 'kafkajs';
import kafka from './KafkaConfig';

export type ConsumerFunction = (payload: EachMessagePayload) => Promise<void>

export default class KafkaConsumer {
    consumer: Consumer;
    topic: ConsumerSubscribeTopic;
    eachMessage: ConsumerFunction;

    constructor(groupId: string, topic: ConsumerSubscribeTopic, eachMessage: ConsumerFunction) {
        this.consumer = kafka.consumer({groupId});
        this.topic = topic;
        this.eachMessage = eachMessage;
    }

    async start(){
        await this.consumer.connect();
        await this.consumer.subscribe(this.topic);
        await this.consumer.run({ eachMessage: this.eachMessage });
    }

    async stop(){
        this.consumer.disconnect();
    }
}
